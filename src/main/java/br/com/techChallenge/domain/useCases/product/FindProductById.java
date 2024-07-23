package br.com.techChallenge.domain.useCases.product;

import br.com.techChallenge.domain.entity.product.ProductDomain;

import java.util.UUID;

public interface FindProductById {

    ProductDomain execute(UUID id);
}
