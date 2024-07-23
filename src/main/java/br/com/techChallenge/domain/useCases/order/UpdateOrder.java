package br.com.techChallenge.domain.useCases.order;

import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;

import java.util.List;
import java.util.UUID;

public interface UpdateOrder {

    OrderDomain execute(UUID idOrder, String cpf, List<OrderItemDomain> items, PaymentType provider);
}
