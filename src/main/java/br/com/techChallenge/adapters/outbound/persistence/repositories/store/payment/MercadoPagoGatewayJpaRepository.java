package br.com.techChallenge.adapters.outbound.persistence.repositories.store.payment;

import br.com.techChallenge.adapters.outbound.persistence.entities.store.payment.MercadoPagoGatewayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MercadoPagoGatewayJpaRepository extends JpaRepository<MercadoPagoGatewayEntity, UUID> {
}
