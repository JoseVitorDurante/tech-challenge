package br.com.techChallenge.core.ports.order;

import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.domain.order.enums.StatusOrder;
import br.com.techChallenge.core.domain.order.item.OrderItemDomain;

import java.util.List;
import java.util.UUID;

public interface OrderServicePort {

    OrderDomain findById(UUID id);

    List<OrderDomain> findAll();

    OrderDomain save(OrderDomain orderDomain, String cpf);

    OrderDomain update(UUID idOrder, String cpf, List<OrderItemDomain> items);

    void updateStatus(UUID id, StatusOrder status);

    List<OrderDomain> findByCpf(String cpf);
}
