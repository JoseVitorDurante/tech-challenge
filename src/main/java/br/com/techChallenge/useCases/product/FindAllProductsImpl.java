package br.com.techChallenge.useCases.product;

import br.com.techChallenge.domain.entity.product.ProductDomain;
import br.com.techChallenge.domain.persistence.product.ProductPersistence;
import br.com.techChallenge.domain.useCases.product.FindAllProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllProductsImpl implements FindAllProducts {

    private final ProductPersistence productPersistence;
    @Override
    public List<ProductDomain> execute() {
        return productPersistence.findAll();
    }
}
