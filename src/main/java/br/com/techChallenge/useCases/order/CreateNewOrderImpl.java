package br.com.techChallenge.useCases.order;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.order.enums.StatusOrder;
import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;
import br.com.techChallenge.domain.entity.payment.PaymentDomain;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;
import br.com.techChallenge.domain.persistence.order.OrderPersistence;
import br.com.techChallenge.domain.useCases.customer.FindCustomerByCPF;
import br.com.techChallenge.domain.useCases.order.CreateNewOrder;
import br.com.techChallenge.domain.useCases.order.item.CreateNewOrderItem;
import br.com.techChallenge.domain.useCases.payment.MakeANewPayment;
import br.com.techChallenge.domain.useCases.payment.ProcessPayment;
import br.com.techChallenge.domain.useCases.product.FindProductById;
import br.com.techChallenge.domain.useCases.product.FindProductByIdAndIdStore;
import br.com.techChallenge.domain.useCases.store.FindStoreById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateNewOrderImpl implements CreateNewOrder {

    private final OrderPersistence orderPersistence;
    private final CreateNewOrderItem createNewOrderItem;
    private final FindStoreById findStoreById;
    private final FindProductById findProductById;
    private final FindProductByIdAndIdStore findProductByIdAndIdStore;
    private final MakeANewPayment makeANewPayment;
    private final FindCustomerByCPF findCustomerByCPF;
    private final Map<String, ProcessPayment> processPaymentList;
    @Override
    public OrderDomain execute(OrderDomain orderDomain, String cpf, PaymentType provider) {

        orderDomain.validatedStore(findStoreById);
        orderDomain.validatedQuantityItems(orderDomain.getItems());
        orderDomain.validatedItemOrException(findProductByIdAndIdStore);


        if (cpf != null) {
            CustomerDomain customerDomain = findCustomerByCPF.execute(cpf);
            orderDomain.setIdCustomer(customerDomain.getId());
            orderDomain.setCustomer(customerDomain);
        }

        orderDomain.calculateTotal(findProductById);

        orderDomain.setStatus(StatusOrder.RECEIVED);

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

        return orderPersistence.save(orderDomainSave);
    }
}
