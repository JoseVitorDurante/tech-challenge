package br.com.techChallenge.useCases.order;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.order.enums.StatusOrder;
import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;
import br.com.techChallenge.domain.entity.payment.PaymentDomain;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;
import br.com.techChallenge.domain.persistence.order.OrderPersistence;
import br.com.techChallenge.domain.persistence.payment.PaymentPersistence;
import br.com.techChallenge.domain.useCases.customer.FindCustomerByCPF;
import br.com.techChallenge.domain.useCases.order.FindOrderById;
import br.com.techChallenge.domain.useCases.order.UpdateOrder;
import br.com.techChallenge.domain.useCases.order.item.CreateNewOrderItem;
import br.com.techChallenge.domain.useCases.payment.MakeANewPayment;
import br.com.techChallenge.domain.useCases.payment.ProcessPayment;
import br.com.techChallenge.domain.useCases.product.FindProductById;
import br.com.techChallenge.domain.useCases.product.FindProductByIdAndIdStore;
import br.com.techChallenge.useCases.order.exceptions.OrderStatusNotReceived;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateOrderImpl implements UpdateOrder {

    private final FindOrderById findOrderById;
    private final FindCustomerByCPF findCustomerByCPF;
    private final OrderPersistence orderPersistence;
    private final FindProductById findProductById;
    private final FindProductByIdAndIdStore findProductByIdAndIdStore;
    private final CreateNewOrderItem createNewOrderItem;
    private final MakeANewPayment makeANewPayment;
    private final PaymentPersistence paymentPersistencePort;
    private final Map<String, ProcessPayment> processPaymentList;

    @Override
    public OrderDomain execute(UUID idOrder, String cpf, List<OrderItemDomain> items, PaymentType provider) {

        OrderDomain orderDomain = findOrderById.execute(idOrder);
        orderDomain.validatedQuantityItems(items);

        if (!orderDomain.getStatus().equals(StatusOrder.RECEIVED))
            throw new OrderStatusNotReceived();

        orderDomain.validatedPayments();
        orderDomain.validatedItemOrException(findProductByIdAndIdStore);

        items.forEach(item -> {
            Optional<OrderItemDomain> optionalOrderItemDomain = findOrderItemDomain(orderDomain, item);

            optionalOrderItemDomain.ifPresentOrElse(
                    existingItem -> existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity()),
                    () -> orderDomain.getItems().add(item));
        });

        if (cpf != null) {
            CustomerDomain customerDomain = findCustomerByCPF.execute(cpf);
                orderDomain.setIdCustomer(customerDomain.getId());
                orderDomain.setCustomer(customerDomain);

        }

        orderDomain.setStatus(StatusOrder.RECEIVED);

        UUID oldIdPayment = orderDomain.getPayment().getId();

        orderDomain.setPayment(null);
        orderDomain.setIdPayment(null);

        orderDomain.calculateTotal(findProductById);

        OrderDomain orderDomainSave = orderPersistence.save(orderDomain);

        List<OrderItemDomain> savedItems = orderDomain.getItems().stream()
                .map(item -> {
                    item.setIdOrder(orderDomainSave.getId());
                    item.setOrder(orderDomainSave);
                    return createNewOrderItem.execute(item);
                })
                .collect(Collectors.toList());

        orderDomainSave.setItems(savedItems);

        ProcessPayment processPayment = processPaymentList.get(provider.name());
        if (processPayment == null)
            throw new IllegalArgumentException("Invalid payment provider: " + provider);

        PaymentDomain payment = makeANewPayment.execute(orderDomainSave, provider, processPayment);
        orderDomainSave.setPayment(payment);
        orderDomainSave.setIdPayment(payment.getId());

        OrderDomain save = orderPersistence.save(orderDomainSave);

        paymentPersistencePort.deleteByID(oldIdPayment);

        return save;
    }

    private Optional<OrderItemDomain> findOrderItemDomain(OrderDomain orderDomain, OrderItemDomain item) {
        return orderDomain.getItems().stream()
                .filter(orderItem -> orderItem.getIdProduct().equals(item.getIdProduct()))
                .findFirst();
    }
}
