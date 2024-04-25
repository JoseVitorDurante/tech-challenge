package br.com.techChallenge.core.ports.category;

import br.com.techChallenge.core.domain.category.CategoryDomain;

import java.util.List;
import java.util.UUID;

public interface CategoryServicePort {

    CategoryDomain findById(UUID id);

    List<CategoryDomain> findAll();

    CategoryDomain save(CategoryDomain categoryDomain);

    CategoryDomain update(CategoryDomain updateCategoryDomain);

    void delete(CategoryDomain categoryDomain);


}
