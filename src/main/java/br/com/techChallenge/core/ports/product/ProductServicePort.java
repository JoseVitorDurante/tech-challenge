package br.com.techChallenge.core.ports.product;

import br.com.techChallenge.core.domain.product.ProductDomain;

import java.util.List;
import java.util.UUID;

public interface ProductServicePort {

    ProductDomain findById(UUID id);

    List<ProductDomain> findAll();

    List<ProductDomain> findAllByCategory(UUID idCategory);

    ProductDomain save(ProductDomain productDomain);

    ProductDomain update(ProductDomain updateProductDomain);

    void delete(ProductDomain productDomain);
}
