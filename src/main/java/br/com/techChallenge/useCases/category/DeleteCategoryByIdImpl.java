package br.com.techChallenge.useCases.category;

import br.com.techChallenge.useCases.category.exceptions.CategoryNotFound;
import br.com.techChallenge.useCases.category.exceptions.ExistProductInCategory;
import br.com.techChallenge.domain.entity.category.CategoryDomain;
import br.com.techChallenge.domain.entity.product.ProductDomain;
import br.com.techChallenge.domain.port.category.CategoryPersistencePort;
import br.com.techChallenge.domain.port.product.ProductPersistencePort;
import br.com.techChallenge.domain.useCases.category.DeleteCategoryById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeleteCategoryByIdImpl implements DeleteCategoryById {

    private final CategoryPersistencePort categoryPersistencePort;

    private final ProductPersistencePort productPersistencePort;
    @Override
    public void execute(UUID id) {

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
