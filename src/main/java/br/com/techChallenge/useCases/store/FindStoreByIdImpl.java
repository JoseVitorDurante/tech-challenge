package br.com.techChallenge.useCases.store;

import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.port.store.StorePersistencePort;
import br.com.techChallenge.domain.useCases.store.FindStoreById;
import br.com.techChallenge.useCases.store.exceptions.StoreNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindStoreByIdImpl implements FindStoreById {

    final StorePersistencePort storePersistencePort;
    @Override
    public StoreDomain execute(UUID id) {
        return storePersistencePort.findById(id)
                .orElseThrow(StoreNotFound::new);
    }
}
