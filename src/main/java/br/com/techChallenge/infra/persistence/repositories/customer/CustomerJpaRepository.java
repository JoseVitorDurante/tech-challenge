package br.com.techChallenge.infra.persistence.repositories.customer;

import br.com.techChallenge.infra.persistence.entities.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByCpf(String cpf);
}
