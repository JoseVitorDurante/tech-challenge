package br.com.techChallenge.core.services.store;

import br.com.techChallenge.core.domain.store.StoreDomain;
import br.com.techChallenge.core.exceptions.store.StoreNotFound;
import br.com.techChallenge.core.ports.store.StorePersistencePort;
import br.com.techChallenge.core.ports.store.StoreServicePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class StoreServicePortImpl implements StoreServicePort {

    final StorePersistencePort storePersistencePort;

    final ModelMapper modelMapper;

    @Override
    public StoreDomain findById(UUID id) {
        return storePersistencePort.findById(id)
                .orElseThrow(StoreNotFound::new);
    }

    @Override
    public StoreDomain save(StoreDomain storeDomain) {
        storeDomain = storePersistencePort.save(storeDomain);
        return storeDomain;
    }

    @Override
    public List<StoreDomain> findAll() {
        return storePersistencePort.findAll();
    }

    @Override
    public void deleteByID(UUID id) {
        StoreDomain storeDomain = storePersistencePort.findById(id)
                .orElseThrow(StoreNotFound::new);

        storeDomain.setActive(false);

        save(storeDomain);
    }
}
