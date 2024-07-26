package br.com.techChallenge.useCases.store;

import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.persistence.store.StorePersistence;
import br.com.techChallenge.domain.useCases.store.DeleteStoreById;
import br.com.techChallenge.domain.useCases.store.FindStoreById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteStoreByIdImpl implements DeleteStoreById {

    private final StorePersistence storePersistence;
    private final FindStoreById findStoreById;

    @Override
    public void execute(UUID id) {
        StoreDomain storeDomain = findStoreById.execute(id);
        storeDomain.setActive(false);

        storePersistence.save(storeDomain);
    }
}
