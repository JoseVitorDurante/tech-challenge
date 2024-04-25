package br.com.techChallenge.core.ports.category;

import br.com.techChallenge.core.domain.category.CategoryDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryPersistencePort {

    Optional<CategoryDomain> findById(UUID id);

    CategoryDomain save(CategoryDomain categoryDomain);

    void delete(CategoryDomain categoryDomain);

    List<CategoryDomain> findAll();
}
