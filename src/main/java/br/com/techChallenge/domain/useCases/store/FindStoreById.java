package br.com.techChallenge.domain.useCases.store;

import br.com.techChallenge.domain.entity.store.StoreDomain;

import java.util.UUID;

public interface FindStoreById {

    StoreDomain execute(UUID id);
}
