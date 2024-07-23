package br.com.techChallenge.domain.useCases.product;

import br.com.techChallenge.domain.entity.product.ProductDomain;

import java.util.List;
import java.util.UUID;

public interface FindAllByCategoryId {

    List<ProductDomain> execute(UUID idCategory);
}
