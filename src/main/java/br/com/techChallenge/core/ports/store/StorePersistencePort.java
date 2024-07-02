package br.com.techChallenge.core.ports.store;

import br.com.techChallenge.core.domain.store.StoreDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StorePersistencePort {

    Optional<StoreDomain> findById(UUID id);

    StoreDomain save(StoreDomain storeDomain);

    List<StoreDomain> findAll();
}
