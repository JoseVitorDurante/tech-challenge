package br.com.techChallenge.core.ports.order;

import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.domain.order.enums.StatusOrder;
import br.com.techChallenge.core.domain.order.item.OrderItemDomain;
import br.com.techChallenge.core.domain.payment.enums.PaymentType;

import java.util.List;
import java.util.UUID;

public interface OrderServicePort {

    OrderDomain findById(UUID id);

    List<OrderDomain> findAll();

    List<OrderDomain> findAllOrdered();

    OrderDomain save(OrderDomain orderDomain, String cpf, PaymentType provider);

    OrderDomain update(UUID idOrder, String cpf, List<OrderItemDomain> items, PaymentType provider);

    void updateStatus(UUID id, StatusOrder status);

    List<OrderDomain> findByCpf(String cpf);
}
