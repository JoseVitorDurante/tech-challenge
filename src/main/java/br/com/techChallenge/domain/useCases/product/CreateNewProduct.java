package br.com.techChallenge.domain.useCases.product;

import br.com.techChallenge.domain.entity.product.ProductDomain;

public interface CreateNewProduct {

   ProductDomain execute(ProductDomain productDomain);
}
