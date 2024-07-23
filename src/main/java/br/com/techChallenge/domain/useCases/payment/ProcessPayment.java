package br.com.techChallenge.domain.useCases.payment;

import br.com.techChallenge.application.dtos.payment.PaymentIntegrationOrder;
import br.com.techChallenge.application.dtos.payment.PaymentIntegrationResult;

public interface ProcessPayment {

    PaymentIntegrationResult processPayment(PaymentIntegrationOrder paymentIntegrationOrder);

}
