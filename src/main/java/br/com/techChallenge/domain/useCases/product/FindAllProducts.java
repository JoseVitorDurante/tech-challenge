package br.com.techChallenge.domain.useCases.product;

import br.com.techChallenge.domain.entity.product.ProductDomain;

import java.util.List;

public interface FindAllProducts {

    List<ProductDomain> execute();
}
