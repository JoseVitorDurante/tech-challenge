package br.com.techChallenge.core.services.product;

import br.com.techChallenge.core.domain.product.ProductDomain;
import br.com.techChallenge.core.exceptions.category.CategoryNotFound;
import br.com.techChallenge.core.exceptions.customer.CustomerNotFound;
import br.com.techChallenge.core.exceptions.product.ProductInvalidPrice;
import br.com.techChallenge.core.exceptions.product.ProductNotFound;
import br.com.techChallenge.core.ports.category.CategoryPersistencePort;
import br.com.techChallenge.core.ports.product.ProductPersistencePort;
import br.com.techChallenge.core.ports.product.ProductServicePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ProductServicePortImpl implements ProductServicePort {

    final ProductPersistencePort productPersistencePort;

    final CategoryPersistencePort categoryPersistencePort;

    final ModelMapper modelMapper;

    @Override
    public ProductDomain findById(UUID id) {
        return productPersistencePort.findById(id)
                .orElseThrow(ProductNotFound::new);
    }

    @Override
    public List<ProductDomain> findAll() {
        return productPersistencePort.findAll();
    }

    @Override
    public List<ProductDomain> findAllByCategory(UUID idCategory) {
        categoryPersistencePort.findById(idCategory)
                .orElseThrow(CategoryNotFound::new);

        return productPersistencePort.findAllByCategory(idCategory);

    }

    @Override
    public ProductDomain save(ProductDomain productDomain) {
        categoryPersistencePort.findById(productDomain.getIdCategory())
                .orElseThrow(CategoryNotFound::new);

        if (productDomain.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new ProductInvalidPrice();


        return productPersistencePort.save(productDomain);
    }

    @Override
    public ProductDomain update(ProductDomain updateProductDomain) {
        ProductDomain domain = productPersistencePort.findById(updateProductDomain.getId())
                .orElseThrow(CustomerNotFound::new);

        modelMapper.map(updateProductDomain, domain);

        return save(domain);
    }

    @Override
    public void delete(ProductDomain productDomain) {
        productPersistencePort.findById(productDomain.getId())
                .orElseThrow(ProductNotFound::new);
        productPersistencePort.delete(productDomain);
    }
}
