package br.com.techChallenge.domain.persistence.order.item;

import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;

import java.util.Optional;
import java.util.UUID;

public interface OrderItemPersistence {

    Optional<OrderItemDomain> findById(UUID id);

    OrderItemDomain save(OrderItemDomain orderItemDomain);
}
