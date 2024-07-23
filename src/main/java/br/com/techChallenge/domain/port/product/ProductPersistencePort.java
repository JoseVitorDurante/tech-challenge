package br.com.techChallenge.domain.port.product;

import br.com.techChallenge.domain.entity.product.ProductDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPersistencePort {

    Optional<ProductDomain> findById(UUID id);

    Optional<ProductDomain> findByIdAndIdStore(UUID idProduct, UUID idStore);

    ProductDomain save(ProductDomain productDomain);

    void deleteByID(UUID id);

    List<ProductDomain> findAll();

    List<ProductDomain> findAllByCategory(UUID idCategory);
}
