package br.com.techChallenge.adapters.outbound.persistence.repositories.category;

import br.com.techChallenge.adapters.outbound.persistence.entities.category.CategoryEntity;
import br.com.techChallenge.adapters.outbound.persistence.entities.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {

}
