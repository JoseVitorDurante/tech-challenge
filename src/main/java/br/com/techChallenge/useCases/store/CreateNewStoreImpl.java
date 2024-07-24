package br.com.techChallenge.useCases.store;

import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.persistence.store.StorePersistence;
import br.com.techChallenge.domain.useCases.store.CreateNewStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewStoreImpl implements CreateNewStore {

    private final StorePersistence storePersistence;
    @Override
    public StoreDomain execute(StoreDomain storeDomain) {
        storeDomain = storePersistence.save(storeDomain);
        return storeDomain;
    }
}
