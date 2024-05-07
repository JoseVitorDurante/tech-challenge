package br.com.techChallenge.core.ports.order.item;

import br.com.techChallenge.core.domain.order.item.OrderItemDomain;

import java.util.Optional;
import java.util.UUID;

public interface OrderItemPersistencePort {

    Optional<OrderItemDomain> findById(UUID id);

    OrderItemDomain save(OrderItemDomain orderItemDomain);
}
