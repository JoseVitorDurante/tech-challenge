package br.com.techChallenge.adapters.outbound.persistence.repositories.order.item;

import br.com.techChallenge.adapters.outbound.persistence.entities.order.item.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, UUID> {

}
