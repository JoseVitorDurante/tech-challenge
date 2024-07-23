package br.com.techChallenge.useCases.product;

import br.com.techChallenge.useCases.product.exceptions.ProductNotFound;
import br.com.techChallenge.domain.entity.product.ProductDomain;
import br.com.techChallenge.domain.port.product.ProductPersistencePort;
import br.com.techChallenge.domain.useCases.product.FindProductById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindProductByIdImpl implements FindProductById {

    private final ProductPersistencePort productPersistencePort;
    @Override
    public ProductDomain execute(UUID id) {
        return productPersistencePort.findById(id)
                .orElseThrow(ProductNotFound::new);
    }
}
