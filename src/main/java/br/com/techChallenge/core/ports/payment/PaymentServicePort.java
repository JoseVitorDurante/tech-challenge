package br.com.techChallenge.core.ports.payment;

import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.domain.payment.PaymentDomain;
import br.com.techChallenge.core.domain.payment.enums.PaymentType;

import java.util.UUID;

public interface PaymentServicePort {

    PaymentDomain createPayment(OrderDomain orderDomain, PaymentType provider);

    PaymentDomain findById(UUID idPayment);
}