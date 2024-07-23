package br.com.techChallenge.useCases.store;

import br.com.techChallenge.useCases.store.exceptions.StoreNotFound;
import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.port.store.StorePersistencePort;
import br.com.techChallenge.domain.useCases.store.DeleteStoreById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteStoreByIdImpl implements DeleteStoreById {

    private final StorePersistencePort storePersistencePort;
    @Override
    public void execute(UUID id) {
        StoreDomain storeDomain = storePersistencePort.findById(id)
                .orElseThrow(StoreNotFound::new);

        storeDomain.setActive(false);

        storePersistencePort.save(storeDomain);
    }
}
