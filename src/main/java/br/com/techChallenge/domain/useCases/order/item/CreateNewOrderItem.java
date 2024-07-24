package br.com.techChallenge.domain.useCases.order.item;

import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;

public interface CreateNewOrderItem {

    OrderItemDomain execute(OrderItemDomain orderItemDomain);
}
