package br.com.techChallenge.adapters.outbound.persistence.repositories.order;

import br.com.techChallenge.adapters.outbound.persistence.entities.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

    List<OrderEntity> findByIdCustomer(UUID idCustomer);

}
