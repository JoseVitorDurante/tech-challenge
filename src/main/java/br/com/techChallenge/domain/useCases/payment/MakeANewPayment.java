package br.com.techChallenge.domain.useCases.payment;

import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.payment.PaymentDomain;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;

public interface MakeANewPayment {

    PaymentDomain execute(OrderDomain orderDomain, PaymentType provider, ProcessPayment processPayment);
}
