package br.com.techChallenge.useCases.payment;

import br.com.techChallenge.application.dtos.payment.PaymentIntegrationOrder;
import br.com.techChallenge.application.dtos.payment.PaymentIntegrationResult;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;
import br.com.techChallenge.domain.useCases.payment.ProcessPayment;
import org.springframework.stereotype.Component;

@Component(PaymentType.CIELO_QUALIFIER)
public class ProcessPaymentWithCieloImpl implements ProcessPayment {

    @Override
    public PaymentIntegrationResult processPayment(PaymentIntegrationOrder paymentIntegrationOrder) {
        System.out.println("Processing payment with Cielo");
        throw new RuntimeException("Not implemented");
    }
}
