package br.com.techChallenge.core.services.order;

import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.domain.order.enums.StatusOrder;
import br.com.techChallenge.core.domain.order.item.OrderItemDomain;
import br.com.techChallenge.core.domain.customer.CustomerDomain;
import br.com.techChallenge.core.exceptions.order.EmptyOrderItems;
import br.com.techChallenge.core.exceptions.order.OrderNotFound;
import br.com.techChallenge.core.exceptions.order.CustomerInOrderNotFound;
import br.com.techChallenge.core.exceptions.order.ProductInOrderNotFound;
import br.com.techChallenge.core.exceptions.order.item.EmptyQuantityItems;
import br.com.techChallenge.core.exceptions.product.ProductNotFound;
import br.com.techChallenge.core.ports.order.OrderPersistencePort;
import br.com.techChallenge.core.ports.order.OrderServicePort;
import br.com.techChallenge.core.ports.order.item.OrderItemPersistencePort;
import br.com.techChallenge.core.ports.customer.CustomerPersistencePort;
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

    final CustomerPersistencePort customerPersistencePort;

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
            CustomerDomain customerDomain = validatedExisCustomerOrException(cpf);
            orderDomain.setIdCustomer(customerDomain.getId());
            orderDomain.setCustomer(customerDomain);
        }

        calculateTotal(orderDomain);

        orderDomain.setStatus(StatusOrder.RECEIVED);

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
            CustomerDomain customerDomain = validatedExisCustomerOrException(cpf);
            orderDomain.setIdCustomer(customerDomain.getId());
            orderDomain.setCustomer(customerDomain);
        }

        orderDomain.setStatus(StatusOrder.RECEIVED);

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
        CustomerDomain customerDomain = validatedExisCustomerOrException(cpf);
        return orderPersistencePort.findByIdCustomer(customerDomain.getId());
    }

    private CustomerDomain validatedExisCustomerOrException(String cpf) {
        return customerPersistencePort.findByCpf(cpf)
                .orElseThrow(() -> new CustomerInOrderNotFound("Customer with cpf " + cpf + " not found"));
    }

    private void validatedItemOrException(List<OrderItemDomain> items) {
        items.forEach(item -> productPersistencePort.findById(item.getIdProduct())
                .orElseThrow(() -> new ProductInOrderNotFound("Product with id " + item.getIdProduct() + " not found")));
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
