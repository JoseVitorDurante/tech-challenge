package br.com.techChallenge.domain.useCases.payment;

import br.com.techChallenge.domain.entity.payment.PaymentDomain;

import java.util.UUID;

public interface FindPaymentById {

    PaymentDomain execute(UUID idPayment);
}
