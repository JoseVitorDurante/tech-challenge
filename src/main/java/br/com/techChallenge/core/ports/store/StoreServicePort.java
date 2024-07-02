package br.com.techChallenge.core.ports.store;

import br.com.techChallenge.core.domain.store.StoreDomain;

import java.util.List;
import java.util.UUID;

public interface StoreServicePort {
    StoreDomain findById(UUID id);

    StoreDomain save(StoreDomain storeDomain);

    void deleteByID(UUID id);

    List<StoreDomain> findAll();
}
