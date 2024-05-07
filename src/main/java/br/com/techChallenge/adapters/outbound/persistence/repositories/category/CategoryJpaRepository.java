package br.com.techChallenge.adapters.outbound.persistence.repositories.category;

import br.com.techChallenge.adapters.outbound.persistence.entities.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {

}
