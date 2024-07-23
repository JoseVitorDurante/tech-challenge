package br.com.techChallenge.infra.persistence.repositories.store;


import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.port.store.StorePersistencePort;
import br.com.techChallenge.infra.persistence.entities.store.StoreEntity;
import br.com.techChallenge.useCases.store.exceptions.StoreNotFound;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class StorePersistencePortImpl implements StorePersistencePort {

    private final StoreJpaRepository storeJpaRepository;
    private final ModelMapper modelMapper;


    @Override
    public StoreDomain save(StoreDomain storeDomain) {
        StoreEntity storeEntity = modelMapper.map(storeDomain, StoreEntity.class);
        StoreEntity savedStoreEntity = storeJpaRepository.save(storeEntity);
        storeEntity.setIdMercadoPagoGateway(savedStoreEntity.getMercadoPagoGateway().getId());
        savedStoreEntity = storeJpaRepository.save(storeEntity);
        return modelMapper.map(savedStoreEntity, StoreDomain.class);
    }

    @Override
    public Optional<StoreDomain> findById(UUID id) {
        return storeJpaRepository.findById(id)
                .map(storeEntity -> modelMapper.map(storeEntity, StoreDomain.class));
    }

    @Override
    public List<StoreDomain> findAll() {
        return storeJpaRepository.findAll().stream()
                .map(storeEntity -> modelMapper.map(storeEntity, StoreDomain.class))
                .toList();
    }

    public StoreEntity entityfindById(UUID id) {
        return storeJpaRepository.findById(id).orElseThrow(StoreNotFound::new);
    }
}
