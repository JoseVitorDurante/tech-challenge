package br.com.techChallenge.core.services.order;

import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.domain.order.enums.StatusOrder;
import br.com.techChallenge.core.domain.order.item.OrderItemDomain;
import br.com.techChallenge.core.domain.person.PersonDomain;
import br.com.techChallenge.core.exceptions.order.EmptyOrderItems;
import br.com.techChallenge.core.exceptions.order.OrderNotFound;
import br.com.techChallenge.core.exceptions.order.PersonInOrderNotFound;
import br.com.techChallenge.core.exceptions.order.ProductInOrderNotFound;
import br.com.techChallenge.core.exceptions.order.item.EmptyQuantityItems;
import br.com.techChallenge.core.exceptions.product.ProductNotFound;
import br.com.techChallenge.core.ports.order.OrderPersistencePort;
import br.com.techChallenge.core.ports.order.OrderServicePort;
import br.com.techChallenge.core.ports.order.item.OrderItemPersistencePort;
import br.com.techChallenge.core.ports.person.PersonPersistencePort;
import br.com.techChallenge.core.ports.product.ProductPersistencePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderServicePortImpl implements OrderServicePort {

    final OrderPersistencePort orderPersistencePort;

    final ProductPersistencePort productPersistencePort;

    final PersonPersistencePort personPersistencePort;

    final OrderItemPersistencePort orderItemPersistencePort;

    final ModelMapper modelMapper;

    @Override
    public OrderDomain findById(UUID id) {
        return orderPersistencePort.findById(id)
                .orElseThrow(OrderNotFound::new);
    }

    @Override
    public List<OrderDomain> findAll() {
        return orderPersistencePort.findAll();
    }

    @Override
    public OrderDomain save(OrderDomain orderDomain, String cpf) {
        validatedQuantityItems(orderDomain.getItems());

        validatedItemOrException(orderDomain.getItems());

        if (cpf != null) {
            orderDomain.setIdPerson(validatedExistPersonOrException(cpf).getId());
        }

        calculateTotal(orderDomain);

        orderDomain.setStatus(StatusOrder.INITIALIZED);

        OrderDomain save = orderPersistencePort.save(orderDomain);

        List<OrderItemDomain> savedItems = orderDomain.getItems().stream()
                .map(item -> {
                    item.setIdOrder(save.getId());
                    item.setOrder(save);
                    return orderItemPersistencePort.save(item);
                })
                .collect(Collectors.toList());

        save.setItems(savedItems);
        return orderPersistencePort.save(save);
    }

    @Override
    public void calculateTotal(OrderDomain orderDomain) {
        orderDomain.setTotal(orderDomain.getItems().stream()
                .map(item -> productPersistencePort.findById(item.getIdProduct())
                        .orElseThrow(ProductNotFound::new)
                        .getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow(ProductNotFound::new));
    }

    @Override
    public OrderDomain update(UUID idOrder, String cpf, List<OrderItemDomain> items) {
        validatedQuantityItems(items);

        OrderDomain orderDomain = orderPersistencePort.findById(idOrder)
                .orElseThrow(OrderNotFound::new);

        validatedItemOrException(items);

        items.forEach(item -> {
            Optional<OrderItemDomain> optionalOrderItemDomain = findOrderItemDomain(orderDomain, item);

            optionalOrderItemDomain.ifPresentOrElse(
                    existingItem -> existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity()),
                    () -> orderDomain.getItems().add(item));
        });

        if (cpf != null) {
            orderDomain.setIdPerson(validatedExistPersonOrException(cpf).getId());
        }

        orderDomain.setStatus(StatusOrder.INITIALIZED);

        calculateTotal(orderDomain);

        OrderDomain save = orderPersistencePort.save(orderDomain);

        List<OrderItemDomain> savedItems = orderDomain.getItems().stream()
                .map(item -> {
                    item.setIdOrder(save.getId());
                    item.setOrder(save);
                    return orderItemPersistencePort.save(item);
                })
                .collect(Collectors.toList());

        save.setItems(savedItems);
        return orderPersistencePort.save(save);
    }

    @Override
    public void updateStatus(UUID id, StatusOrder status) {
        OrderDomain orderDomain = orderPersistencePort.findById(id)
                .orElseThrow(OrderNotFound::new);

        orderDomain.setStatus(status);

        orderPersistencePort.save(orderDomain);
    }

    @Override
    public List<OrderDomain> findByCpf(String cpf) {
        PersonDomain personDomain = validatedExistPersonOrException(cpf);
        return orderPersistencePort.findByIdPerson(personDomain.getId());
    }

    private PersonDomain validatedExistPersonOrException(String cpf) {
        return personPersistencePort.findByCpf(cpf)
                .orElseThrow(() -> new PersonInOrderNotFound("Person with cpf " + cpf + " not found"));
    }

    private void validatedItemOrException(List<OrderItemDomain> items) {
        items.forEach(item -> productPersistencePort.findById(item.getIdProduct())
                .orElseThrow(() -> new ProductInOrderNotFound("Order with id " + item.getIdProduct() + " not found")));
    }

    private static void validatedQuantityItems(List<OrderItemDomain> items) {
        if (items.isEmpty()) {
            throw new EmptyOrderItems();
        }

        if (items.stream().anyMatch(item -> item.getQuantity() == 0)) {
            throw new EmptyQuantityItems();
        }
    }

    private Optional<OrderItemDomain> findOrderItemDomain(OrderDomain orderDomain, OrderItemDomain item) {
        return orderDomain.getItems().stream()
                .filter(orderItem -> orderItem.getIdProduct().equals(item.getIdProduct()))
                .findFirst();
    }
}
