package br.com.techChallenge.useCases.category;

import br.com.techChallenge.domain.entity.category.CategoryDomain;
import br.com.techChallenge.domain.port.category.CategoryPersistencePort;
import br.com.techChallenge.domain.useCases.category.FindAllCategories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllCategoriesImpl implements FindAllCategories {

    private final CategoryPersistencePort categoryPersistencePort;
    @Override
    public List<CategoryDomain> execute() {
        return categoryPersistencePort.findAll();
    }
}
