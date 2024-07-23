package br.com.techChallenge.useCases.product;

import br.com.techChallenge.domain.entity.product.ProductDomain;
import br.com.techChallenge.domain.useCases.product.FindProductById;
import br.com.techChallenge.domain.useCases.product.UpdateProduct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductImpl implements UpdateProduct {

    private final FindProductById findProductById;
    private final CreateNewProductImpl createNewProduct;
    private final ModelMapper modelMapper;
    @Override
    public ProductDomain execute(ProductDomain updateProductDomain) {
        ProductDomain domain = findProductById.execute(updateProductDomain.getId());

        modelMapper.map(updateProductDomain, domain);

        return createNewProduct.execute(domain);
    }
}
