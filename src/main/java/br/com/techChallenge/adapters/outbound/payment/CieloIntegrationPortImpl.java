package br.com.techChallenge.adapters.outbound.payment;

import br.com.techChallenge.core.domain.payment.enums.PaymentType;
import br.com.techChallenge.core.dto.payment.PaymentIntegrationOrder;
import br.com.techChallenge.core.dto.payment.PaymentIntegrationResult;
import br.com.techChallenge.core.ports.payment.PaymentIntegrationPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Qualifier(PaymentType.CIELO_QUALIFIER)
public class CieloIntegrationPortImpl implements PaymentIntegrationPort {

    @Override
    public PaymentIntegrationResult processPayment(PaymentIntegrationOrder paymentIntegrationOrder) {
        System.out.println("Processing payment with Cielo");
        throw new RuntimeException("Not implemented");
    }
}
