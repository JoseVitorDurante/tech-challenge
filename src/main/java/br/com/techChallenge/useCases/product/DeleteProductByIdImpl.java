package br.com.techChallenge.useCases.product;

import br.com.techChallenge.domain.port.product.ProductPersistencePort;
import br.com.techChallenge.domain.useCases.product.DeleteProductById;
import br.com.techChallenge.domain.useCases.product.FindProductById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProductByIdImpl implements DeleteProductById {

    private final FindProductById findProductById;
    private final ProductPersistencePort productPersistencePort;

    @Override
    public void execute(UUID id) {
        findProductById.execute(id);
        productPersistencePort.deleteByID(id);
    }
}
