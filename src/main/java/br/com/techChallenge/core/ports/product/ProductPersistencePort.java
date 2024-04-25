package br.com.techChallenge.core.ports.product;

import br.com.techChallenge.core.domain.product.ProductDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPersistencePort {

    Optional<ProductDomain> findById(UUID id);

    ProductDomain save(ProductDomain productDomain);

    void delete(ProductDomain productDomain);

    List<ProductDomain> findAll();

    List<ProductDomain> findAllByCategory(UUID idCategory);
}
