package br.com.techChallenge.useCases.category;

import br.com.techChallenge.domain.entity.category.CategoryDomain;
import br.com.techChallenge.domain.persistence.category.CategoryPersistence;
import br.com.techChallenge.domain.useCases.category.CreateNewCategory;
import br.com.techChallenge.domain.useCases.store.FindStoreById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewCategoryImpl implements CreateNewCategory {

    private final FindStoreById findStoreById;
    private final CategoryPersistence categoryPersistence;

    @Override
    public CategoryDomain execute(CategoryDomain categoryDomain) {
        findStoreById.execute(categoryDomain.getIdStore());
        return categoryPersistence.save(categoryDomain);
    }
}
