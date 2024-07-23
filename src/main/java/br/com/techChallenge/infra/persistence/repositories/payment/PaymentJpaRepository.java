package br.com.techChallenge.infra.persistence.repositories.payment;

import br.com.techChallenge.infra.persistence.entities.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {

}
