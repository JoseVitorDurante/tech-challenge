package br.com.techChallenge.useCases.category;

import br.com.techChallenge.domain.entity.category.CategoryDomain;
import br.com.techChallenge.useCases.category.exceptions.CategoryNotFound;
import br.com.techChallenge.domain.port.category.CategoryPersistencePort;
import br.com.techChallenge.domain.useCases.category.FindCategoryById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindCategoryByIdImpl implements FindCategoryById {

    private final CategoryPersistencePort categoryPersistencePort;
    @Override
    public CategoryDomain execute(UUID id) {
        return categoryPersistencePort.findById(id)
                .orElseThrow(CategoryNotFound::new);
    }
}
