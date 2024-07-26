package br.com.techChallenge.domain.persistence.store;

import br.com.techChallenge.domain.entity.store.StoreDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StorePersistence {

    Optional<StoreDomain> findById(UUID id);

    StoreDomain save(StoreDomain storeDomain);

    List<StoreDomain> findAll();
}
