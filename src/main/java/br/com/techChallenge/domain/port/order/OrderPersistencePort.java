package br.com.techChallenge.domain.port.order;

import br.com.techChallenge.domain.entity.order.OrderDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPersistencePort {

    Optional<OrderDomain> findById(UUID id);

    OrderDomain save(OrderDomain orderDomain);

    List<OrderDomain> findAll();

    List<OrderDomain> findAllOrdered();

    List<OrderDomain> findByIdCustomer(UUID idCustomer);
}
