package br.com.techChallenge.domain.useCases.store;

import br.com.techChallenge.domain.entity.store.StoreDomain;

public interface CreateNewStore {

   StoreDomain execute(StoreDomain storeDomain);
}
