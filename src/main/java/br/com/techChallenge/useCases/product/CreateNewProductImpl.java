package br.com.techChallenge.useCases.product;

import br.com.techChallenge.useCases.product.exceptions.ProductInvalidPrice;
import br.com.techChallenge.domain.entity.product.ProductDomain;
import br.com.techChallenge.domain.port.product.ProductPersistencePort;
import br.com.techChallenge.domain.useCases.category.FindCategoryById;
import br.com.techChallenge.domain.useCases.product.CreateNewProduct;
import br.com.techChallenge.domain.useCases.store.FindStoreById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateNewProductImpl implements CreateNewProduct {

    private final FindCategoryById findCategoryById;
    private final FindStoreById findStoreById;
    private final ProductPersistencePort productPersistencePort;

    @Override
    public ProductDomain execute(ProductDomain productDomain) {

        findCategoryById.execute(productDomain.getIdCategory());
        findStoreById.execute(productDomain.getIdStore());

        if (productDomain.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new ProductInvalidPrice();


        return productPersistencePort.save(productDomain);
    }
}
