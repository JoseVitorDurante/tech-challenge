package br.com.techChallenge.useCases.product;

import br.com.techChallenge.domain.entity.product.ProductDomain;
import br.com.techChallenge.domain.persistence.product.ProductPersistence;
import br.com.techChallenge.domain.useCases.product.FindProductByIdAndIdStore;
import br.com.techChallenge.useCases.order.exceptions.ProductInOrderNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindProductByIdAndIdStoreImpl implements FindProductByIdAndIdStore {

    private final ProductPersistence productPersistence;

    @Override
    public ProductDomain execute(UUID idProduct, UUID idStore) {
        return productPersistence.findByIdAndIdStore(idProduct, idStore)
                .orElseThrow(() -> new ProductInOrderNotFound("Product with id " + idProduct + ", by idStore: " + idStore + " not found"));
    }
}
