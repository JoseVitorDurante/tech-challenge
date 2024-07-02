package br.com.techChallenge.adapters.outbound.persistence.repositories.store;

import br.com.techChallenge.adapters.outbound.persistence.entities.store.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreJpaRepository extends JpaRepository<StoreEntity, UUID> {
}
