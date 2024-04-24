package br.com.techChallenge.adapters.outbound.persistence.repositories.person;

import br.com.techChallenge.adapters.outbound.persistence.entities.person.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, UUID> {

    Optional<PersonEntity> findByCpf(String cpf);
}
