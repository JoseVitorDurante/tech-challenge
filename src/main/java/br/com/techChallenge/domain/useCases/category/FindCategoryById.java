package br.com.techChallenge.domain.useCases.category;

import br.com.techChallenge.domain.entity.category.CategoryDomain;

import java.util.UUID;

public interface FindCategoryById {

    CategoryDomain execute(UUID id);
}
