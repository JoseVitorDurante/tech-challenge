package br.com.techChallenge.core.services.order;

import br.com.techChallenge.core.domain.customer.CustomerDomain;
import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.domain.order.enums.StatusOrder;
import br.com.techChallenge.core.domain.order.item.OrderItemDomain;
import br.com.techChallenge.core.domain.payment.PaymentDomain;
import br.com.techChallenge.core.domain.payment.enums.PaymentStatus;
import br.com.techChallenge.core.domain.payment.enums.PaymentType;
import br.com.techChallenge.core.domain.store.StoreDomain;
import br.com.techChallenge.core.exceptions.order.*;
import br.com.techChallenge.core.exceptions.order.item.EmptyQuantityItems;
import br.com.techChallenge.core.exceptions.product.ProductNotFound;
import br.com.techChallenge.core.exceptions.store.StoreInactive;
import br.com.techChallenge.core.exceptions.store.StoreNotFound;
import br.com.techChallenge.core.ports.customer.CustomerPersistencePort;
import br.com.techChallenge.core.ports.order.OrderPersistencePort;
import br.com.techChallenge.core.ports.order.OrderServicePort;
import br.com.techChallenge.core.ports.order.item.OrderItemPersistencePort;
import br.com.techChallenge.core.ports.payment.PaymentPersistencePort;
import br.com.techChallenge.core.ports.payment.PaymentServicePort;
import br.com.techChallenge.core.ports.product.ProductPersistencePort;
import br.com.techChallenge.core.ports.store.StorePersistencePort;
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

    final PaymentServicePort paymentServicePort;

    final PaymentPersistencePort paymentPersistencePort;

    final StorePersistencePort storePersistencePort;

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
    public List<OrderDomain> findAllOrdered() {
        return orderPersistencePort.findAllOrdered();
    }

    @Override
    public OrderDomain save(OrderDomain orderDomain, String cpf, PaymentType provider) {
        validatedStore(orderDomain);

        validatedQuantityItems(orderDomain.getItems());

        validatedItemOrException(orderDomain.getItems(), orderDomain.getIdStore());

        if (cpf != null) {
            CustomerDomain customerDomain = validatedExisCustomerOrException(cpf);
            orderDomain.setIdCustomer(customerDomain.getId());
            orderDomain.setCustomer(customerDomain);
        }

        calculateTotal(orderDomain);

        orderDomain.setStatus(StatusOrder.RECEIVED);

        OrderDomain orderDomainSave = orderPersistencePort.save(orderDomain);

        List<OrderItemDomain> savedItems = orderDomain.getItems().stream()
                .map(item -> {
                    item.setIdOrder(orderDomainSave.getId());
                    item.setOrder(orderDomainSave);
                    return orderItemPersistencePort.save(item);
                })
                .collect(Collectors.toList());

        orderDomainSave.setItems(savedItems);

        PaymentDomain payment = paymentServicePort.createPayment(orderDomainSave, PaymentType.MERCADO_PAGO);
        orderDomainSave.setPayment(payment);
        orderDomainSave.setIdPayment(payment.getId());

        return orderPersistencePort.save(orderDomainSave);
    }

    public void calculateTotal(OrderDomain orderDomain) {
        orderDomain.setTotal(orderDomain.getItems().stream()
                .map(item -> productPersistencePort.findById(item.getIdProduct())
                        .orElseThrow(ProductNotFound::new)
                        .getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow(ProductNotFound::new));
    }

    @Override
    public OrderDomain update(UUID idOrder, String cpf, List<OrderItemDomain> items, PaymentType provider) {
        validatedQuantityItems(items);

        OrderDomain orderDomain = orderPersistencePort.findById(idOrder)
                .orElseThrow(OrderNotFound::new);

        if (!orderDomain.getStatus().equals(StatusOrder.RECEIVED))
            throw new OrderStatusNotReceived();

        validatedPayments(orderDomain);

        validatedItemOrException(items, orderDomain.getIdStore());

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

        UUID oldIdPayment = orderDomain.getPayment().getId();

        orderDomain.setPayment(null);
        orderDomain.setIdPayment(null);

        calculateTotal(orderDomain);

        OrderDomain orderDomainSave = orderPersistencePort.save(orderDomain);

        List<OrderItemDomain> savedItems = orderDomain.getItems().stream()
                .map(item -> {
                    item.setIdOrder(orderDomainSave.getId());
                    item.setOrder(orderDomainSave);
                    return orderItemPersistencePort.save(item);
                })
                .collect(Collectors.toList());

        orderDomainSave.setItems(savedItems);

        PaymentDomain payment = paymentServicePort.createPayment(orderDomainSave, provider);
        orderDomainSave.setPayment(payment);
        orderDomainSave.setIdPayment(payment.getId());

        OrderDomain save = orderPersistencePort.save(orderDomainSave);

        paymentPersistencePort.deleteByID(oldIdPayment);

        return save;

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

    private void validatedPayments(OrderDomain orderDomain) {
        if (orderDomain.getPayment() != null && orderDomain.getPayment().getStatus().equals(PaymentStatus.APPROVED))
            throw new OrderPaymentApproved();
    }

    private void validatedStore(OrderDomain orderDomain) {
        StoreDomain storeDomain = storePersistencePort.findById(orderDomain.getIdStore())
                .orElseThrow(StoreNotFound::new);

        if (!storeDomain.isActive())
            throw new StoreInactive();

        orderDomain.setStore(storeDomain);
        orderDomain.setIdStore(storeDomain.getId());
    }

    private void validatedItemOrException(List<OrderItemDomain> items, UUID idStore) {
        items.forEach(item -> item.setProduct(productPersistencePort.findByIdAndIdStore(item.getIdProduct(), idStore)
                .orElseThrow(() -> new ProductInOrderNotFound("Product with id " + item.getIdProduct() + ", by idStore: " + idStore + " not found"))));
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
