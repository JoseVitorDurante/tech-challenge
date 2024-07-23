package br.com.techChallenge.domain.useCases.category;

import br.com.techChallenge.domain.entity.category.CategoryDomain;

public interface UpdateCategory {

    CategoryDomain execute(CategoryDomain updateCategoryDomain);
}
