package br.com.techChallenge.infra.persistence.repositories.category;


import br.com.techChallenge.domain.entity.category.CategoryDomain;
import br.com.techChallenge.domain.persistence.category.CategoryPersistence;
import br.com.techChallenge.infra.persistence.entities.category.CategoryEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class CategoryPersistencePortImpl implements CategoryPersistence {

    private final CategoryJpaRepository categoryJpaRepository;
    private final ModelMapper modelMapper;


    @Override
    public Optional<CategoryDomain> findById(UUID id) {
        return categoryJpaRepository.findById(id)
                .map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDomain.class));
    }

    @Override
    public CategoryDomain save(CategoryDomain categoryDomain) {
        CategoryEntity categoryEntity = categoryJpaRepository.save(modelMapper.map(categoryDomain, CategoryEntity.class));
        return modelMapper.map(categoryEntity, CategoryDomain.class);
    }

    @Override
    public void deleteByID(UUID id) {
        categoryJpaRepository.deleteById(id);
    }

    @Override
    public List<CategoryDomain> findAll() {
        List<CategoryEntity> findAll = categoryJpaRepository.findAll();
        if (!findAll.isEmpty()) {
            return findAll.stream()
                    .map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDomain.class))
                    .toList();
        }

        return List.of();
    }
}
