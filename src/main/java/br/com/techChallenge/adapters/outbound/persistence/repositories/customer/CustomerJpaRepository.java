package br.com.techChallenge.adapters.outbound.persistence.repositories.customer;

import br.com.techChallenge.adapters.outbound.persistence.entities.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByCpf(String cpf);
}
