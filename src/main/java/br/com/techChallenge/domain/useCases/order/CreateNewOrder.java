package br.com.techChallenge.domain.useCases.order;

import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;

public interface CreateNewOrder {

   OrderDomain execute(OrderDomain orderDomain, String cpf, PaymentType provider);
}
