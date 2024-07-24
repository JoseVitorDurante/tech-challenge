package br.com.techChallenge.useCases.store;

import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.persistence.store.StorePersistence;
import br.com.techChallenge.domain.useCases.store.FindAllStores;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllStoresImpl implements FindAllStores {

    final StorePersistence storePersistence;

    @Override
    public List<StoreDomain> execute() {
        return storePersistence.findAll();
    }
}
