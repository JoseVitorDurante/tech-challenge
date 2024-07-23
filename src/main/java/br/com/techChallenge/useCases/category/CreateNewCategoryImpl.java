package br.com.techChallenge.useCases.category;

import br.com.techChallenge.useCases.store.exceptions.StoreNotFound;
import br.com.techChallenge.domain.entity.category.CategoryDomain;
import br.com.techChallenge.domain.port.category.CategoryPersistencePort;
import br.com.techChallenge.domain.port.store.StorePersistencePort;
import br.com.techChallenge.domain.useCases.category.CreateNewCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewCategoryImpl implements CreateNewCategory {

    private final StorePersistencePort storePersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;

    @Override
    public CategoryDomain execute(CategoryDomain categoryDomain) {
        storePersistencePort.findById(categoryDomain.getIdStore())
                .orElseThrow(StoreNotFound::new);

        return categoryPersistencePort.save(categoryDomain);
    }
}
