package br.com.techChallenge.core.ports.order.item;

import br.com.techChallenge.core.domain.order.item.OrderItemDomain;

import java.util.UUID;

public interface OrderItemServicePort {

    OrderItemDomain findById(UUID id);

    OrderItemDomain save(OrderItemDomain orderItemDomain);
}
