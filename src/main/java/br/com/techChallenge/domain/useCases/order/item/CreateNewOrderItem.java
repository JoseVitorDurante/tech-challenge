package br.com.techChallenge.domain.useCases.order.item;

import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;

public interface CreateNewOrderItem {

    DomainEntity save(OrderItemDomain orderItemDomain);
}
