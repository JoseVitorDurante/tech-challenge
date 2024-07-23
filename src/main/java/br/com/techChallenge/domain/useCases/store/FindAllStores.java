package br.com.techChallenge.domain.useCases.store;

import br.com.techChallenge.domain.entity.store.StoreDomain;

import java.util.List;

public interface FindAllStores {

    List<StoreDomain> execute();
}
