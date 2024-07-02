package br.com.techChallenge.core.services.category;

import br.com.techChallenge.core.domain.category.CategoryDomain;
import br.com.techChallenge.core.domain.product.ProductDomain;
import br.com.techChallenge.core.exceptions.category.CategoryNotFound;
import br.com.techChallenge.core.exceptions.category.ExistProductInCategory;
import br.com.techChallenge.core.exceptions.store.StoreNotFound;
import br.com.techChallenge.core.ports.category.CategoryPersistencePort;
import br.com.techChallenge.core.ports.category.CategoryServicePort;
import br.com.techChallenge.core.ports.product.ProductPersistencePort;
import br.com.techChallenge.core.ports.store.StorePersistencePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CategoryServicePortImpl implements CategoryServicePort {

    final CategoryPersistencePort categoryPersistencePort;

    final ProductPersistencePort productPersistencePort;

    final StorePersistencePort storePersistencePort;

    final ModelMapper modelMapper;

    @Override
    public CategoryDomain findById(UUID id) {
        return categoryPersistencePort.findById(id)
                .orElseThrow(CategoryNotFound::new);
    }

    @Override
    public List<CategoryDomain> findAll() {
        return categoryPersistencePort.findAll();
    }

    @Override
    public CategoryDomain save(CategoryDomain categoryDomain) {

        storePersistencePort.findById(categoryDomain.getIdStore())
                .orElseThrow(StoreNotFound::new);

        return categoryPersistencePort.save(categoryDomain);
    }

    @Override
    public CategoryDomain update(CategoryDomain updateCategoryDomain) {
        CategoryDomain domain = categoryPersistencePort.findById(updateCategoryDomain.getId())
                .orElseThrow(CategoryNotFound::new);

        modelMapper.map(updateCategoryDomain, domain);

        return save(domain);
    }

    @Override
    public void deleteByID(UUID id) {
        CategoryDomain categoryDomain = categoryPersistencePort.findById(id)
                .orElseThrow(CategoryNotFound::new);

        List<ProductDomain> allByCategory = productPersistencePort.findAllByCategory(categoryDomain.getId());

        if (!allByCategory.isEmpty()) {
            String productDetails = allByCategory.stream()
                    .map(product -> "id: " + product.getId() + ", name: " + product.getName())
                    .collect(Collectors.joining(", "));
            throw new ExistProductInCategory("Exist product in category, details: " + productDetails);
        }

        categoryPersistencePort.deleteByID(id);
    }
}
