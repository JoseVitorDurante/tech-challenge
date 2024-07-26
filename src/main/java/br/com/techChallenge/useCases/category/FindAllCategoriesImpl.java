package br.com.techChallenge.useCases.category;

import br.com.techChallenge.domain.entity.category.CategoryDomain;
import br.com.techChallenge.domain.persistence.category.CategoryPersistence;
import br.com.techChallenge.domain.useCases.category.FindAllCategories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllCategoriesImpl implements FindAllCategories {

    private final CategoryPersistence categoryPersistence;
    @Override
    public List<CategoryDomain> execute() {
        return categoryPersistence.findAll();
    }
}
