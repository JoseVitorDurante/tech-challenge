package br.com.techChallenge.core.services.payment;

import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.domain.payment.PaymentDomain;
import br.com.techChallenge.core.domain.payment.enums.PaymentStatus;
import br.com.techChallenge.core.domain.payment.enums.PaymentType;
import br.com.techChallenge.core.dto.payment.PaymentIntegrationItem;
import br.com.techChallenge.core.dto.payment.PaymentIntegrationOrder;
import br.com.techChallenge.core.dto.payment.PaymentIntegrationResult;
import br.com.techChallenge.core.exceptions.payment.PaymentNotFound;
import br.com.techChallenge.core.ports.payment.PaymentIntegrationPort;
import br.com.techChallenge.core.ports.payment.PaymentPersistencePort;
import br.com.techChallenge.core.ports.payment.PaymentServicePort;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class PaymentServiceService implements PaymentServicePort {

    final Map<String, PaymentIntegrationPort> paymentIntegrationPorts;

    final PaymentPersistencePort paymentPersistencePort;

    @Override
    public PaymentDomain findById(UUID idPayment) {
        return paymentPersistencePort.findById(idPayment).orElseThrow(PaymentNotFound::new);
    }

    @Override
    public PaymentDomain createPayment(OrderDomain orderDomain,
                                       PaymentType provider) {
        PaymentIntegrationPort paymentIntegrationPort = paymentIntegrationPorts.get(provider.name());

        if (paymentIntegrationPort == null)
            throw new IllegalArgumentException("Invalid payment provider: " + provider);

        List<PaymentIntegrationItem> item = new ArrayList<>();

        orderDomain.getItems().forEach(itemDomain -> item.add(new PaymentIntegrationItem(
                itemDomain.getQuantity(),
                itemDomain.getProduct().getPrice().multiply(BigDecimal.valueOf(itemDomain.getQuantity())),
                itemDomain.getProduct().getPrice(),
                itemDomain.getProduct().getName()
        )));


        PaymentIntegrationOrder paymentIntegrationOrder = new PaymentIntegrationOrder(
                UUID.randomUUID(),
                orderDomain.getTotal(),
                item
        );

        PaymentIntegrationResult paymentIntegrationResult = paymentIntegrationPort.processPayment(paymentIntegrationOrder);

        PaymentDomain paymentDomain = new PaymentDomain();
        paymentDomain.setId(paymentIntegrationResult.getPaymentId());
        paymentDomain.setAmount(paymentIntegrationOrder.getAmount());
        paymentDomain.setQrCode(paymentIntegrationResult.getQrCode());
        paymentDomain.setType(provider);
        paymentDomain.setStatus(PaymentStatus.CREATED);
        paymentDomain.setIdOrder(orderDomain.getId());

        return paymentPersistencePort.save(paymentDomain);
    }

}