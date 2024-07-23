package br.com.techChallenge.useCases.product;

import br.com.techChallenge.domain.entity.product.ProductDomain;
import br.com.techChallenge.domain.port.product.ProductPersistencePort;
import br.com.techChallenge.domain.useCases.category.FindCategoryById;
import br.com.techChallenge.domain.useCases.product.FindAllByCategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllByCategoryIdImpl implements FindAllByCategoryId {

    private final FindCategoryById findCategoryById;
    private final ProductPersistencePort productPersistencePort;

    @Override
    public List<ProductDomain> execute(UUID idCategory) {
        findCategoryById.execute(idCategory);

        return productPersistencePort.findAllByCategory(idCategory);
    }
}
