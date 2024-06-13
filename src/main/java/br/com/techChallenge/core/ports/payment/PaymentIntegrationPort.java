package br.com.techChallenge.core.ports.payment;

import br.com.techChallenge.core.dto.payment.PaymentIntegrationOrder;
import br.com.techChallenge.core.dto.payment.PaymentIntegrationResult;

public interface PaymentIntegrationPort {

    PaymentIntegrationResult processPayment(PaymentIntegrationOrder paymentIntegrationOrder);

}
