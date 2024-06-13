package br.com.techChallenge.adapters.outbound.persistence.repositories.payment;

import br.com.techChallenge.adapters.outbound.persistence.entities.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {

}
