package br.com.techChallenge.infra.persistence.repositories.product;

import br.com.techChallenge.infra.persistence.entities.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<ProductEntity> findByIdAndIdStore(UUID id, UUID idStore);

    List<ProductEntity> findAllByIdCategory(UUID idCategory);
}
