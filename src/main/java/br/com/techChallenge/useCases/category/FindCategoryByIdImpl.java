package br.com.techChallenge.useCases.category;

import br.com.techChallenge.domain.entity.category.CategoryDomain;
import br.com.techChallenge.useCases.category.exceptions.CategoryNotFound;
import br.com.techChallenge.domain.persistence.category.CategoryPersistence;
import br.com.techChallenge.domain.useCases.category.FindCategoryById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindCategoryByIdImpl implements FindCategoryById {

    private final CategoryPersistence categoryPersistence;
    @Override
    public CategoryDomain execute(UUID id) {
        return categoryPersistence.findById(id)
                .orElseThrow(CategoryNotFound::new);
    }
}
