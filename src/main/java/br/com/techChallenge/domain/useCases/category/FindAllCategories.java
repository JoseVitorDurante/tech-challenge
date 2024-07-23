package br.com.techChallenge.domain.useCases.category;

import br.com.techChallenge.domain.entity.category.CategoryDomain;

import java.util.List;

public interface FindAllCategories {

    List<CategoryDomain> execute();
}
