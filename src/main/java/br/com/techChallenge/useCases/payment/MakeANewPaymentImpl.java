package br.com.techChallenge.useCases.payment;

import br.com.techChallenge.application.dtos.payment.PaymentIntegrationItem;
import br.com.techChallenge.application.dtos.payment.PaymentIntegrationOrder;
import br.com.techChallenge.application.dtos.payment.PaymentIntegrationResult;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.payment.PaymentDomain;
import br.com.techChallenge.domain.entity.payment.enums.PaymentStatus;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;
import br.com.techChallenge.domain.useCases.payment.ProcessPayment;
import br.com.techChallenge.domain.port.payment.PaymentPersistencePort;
import br.com.techChallenge.domain.useCases.payment.MakeANewPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MakeANewPaymentImpl implements MakeANewPayment {

    final PaymentPersistencePort paymentPersistencePort;
    @Override
    public PaymentDomain execute(OrderDomain orderDomain, PaymentType provider, ProcessPayment processPayment) {

        List<PaymentIntegrationItem> item = new ArrayList<>();

        orderDomain.getItems().forEach(itemDomain -> item.add(new PaymentIntegrationItem(
                itemDomain.getQuantity(),
                itemDomain.getProduct().getPrice().multiply(BigDecimal.valueOf(itemDomain.getQuantity())),
                itemDomain.getProduct().getPrice(),
                itemDomain.getProduct().getName()
        )));


        PaymentIntegrationOrder paymentIntegrationOrder = new PaymentIntegrationOrder(
                orderDomain.getStore().getId(),
                UUID.randomUUID(),
                orderDomain.getTotal(),
                item
        );

        PaymentIntegrationResult paymentIntegrationResult = processPayment.processPayment(paymentIntegrationOrder);

        PaymentDomain paymentDomain = new PaymentDomain();
        paymentDomain.setId(paymentIntegrationResult.getPaymentId());
        paymentDomain.setAmount(paymentIntegrationOrder.getAmount());
        paymentDomain.setQrCode(paymentIntegrationResult.getQrCode());
        paymentDomain.setType(provider);
        paymentDomain.setStatus(PaymentStatus.PENDING);
        paymentDomain.setIdOrder(orderDomain.getId());

        return paymentPersistencePort.save(paymentDomain);
    }
}
