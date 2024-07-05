package br.com.techChallenge.adapters.outbound.persistence.repositories.order;

import br.com.techChallenge.adapters.outbound.persistence.entities.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

    List<OrderEntity> findByIdCustomer(UUID idCustomer);

    @Query("SELECT o FROM orders o WHERE o.status <> 'FINISHED' " +
    "ORDER BY CASE o.status " +
    "WHEN 'READY_FOR_PICKUP' THEN 1 " +
    "WHEN 'IN_PREPARATION' THEN 2 " +
    "WHEN 'RECEIVED' THEN 3 " +
    "ELSE 4 " +
    "END, " +
    "o.createdAt ASC")
    List<OrderEntity> findAllOrderedByStatusAndDate();

}
