package br.com.techChallenge.adapters.outbound.persistence.repositories.product;

import br.com.techChallenge.adapters.outbound.persistence.entities.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findAllByIdCategory(UUID idCategory);
}
