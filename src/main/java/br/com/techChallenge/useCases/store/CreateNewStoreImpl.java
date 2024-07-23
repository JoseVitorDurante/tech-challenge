package br.com.techChallenge.useCases.store;

import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.port.store.StorePersistencePort;
import br.com.techChallenge.domain.useCases.store.CreateNewStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewStoreImpl implements CreateNewStore {

    private final StorePersistencePort storePersistencePort;
    @Override
    public StoreDomain execute(StoreDomain storeDomain) {
        storeDomain = storePersistencePort.save(storeDomain);
        return storeDomain;
    }
}
