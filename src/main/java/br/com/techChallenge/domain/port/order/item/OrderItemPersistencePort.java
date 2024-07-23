package br.com.techChallenge.domain.port.order.item;

import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;

import java.util.Optional;
import java.util.UUID;

public interface OrderItemPersistencePort {

    Optional<OrderItemDomain> findById(UUID id);

    OrderItemDomain save(OrderItemDomain orderItemDomain);
}
