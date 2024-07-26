package br.com.techChallenge.infra.persistence.repositories.product;


import br.com.techChallenge.domain.entity.product.ProductDomain;
import br.com.techChallenge.domain.persistence.product.ProductPersistence;
import br.com.techChallenge.infra.persistence.entities.product.ProductEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class ProductPersistencePortImpl implements ProductPersistence {

    private final ProductJpaRepository productJpaRepository;
    private final ModelMapper modelMapper;


    @Override
    public Optional<ProductDomain> findById(UUID id) {
        return productJpaRepository.findById(id)
                .map(productEntity -> modelMapper.map(productEntity, ProductDomain.class));
    }

    @Override
    public Optional<ProductDomain> findByIdAndIdStore(UUID idProduct, UUID idStore) {
        return productJpaRepository.findByIdAndIdStore(idProduct, idStore)
                .map(productEntity -> modelMapper.map(productEntity, ProductDomain.class));
    }

    @Override
    public ProductDomain save(ProductDomain productDomain) {
        ProductEntity map = modelMapper.map(productDomain, ProductEntity.class);
        ProductEntity productEntity = productJpaRepository.save(map);
        return modelMapper.map(productEntity, ProductDomain.class);
    }

    @Override
    public void deleteByID(UUID id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    public List<ProductDomain> findAll() {
        List<ProductEntity> findAll = productJpaRepository.findAll();
        if (!findAll.isEmpty()) {
            return findAll.stream()
                    .map(productEntity -> modelMapper.map(productEntity, ProductDomain.class))
                    .toList();
        }

        return List.of();
    }

    @Override
    public List<ProductDomain> findAllByCategory(UUID idCategory) {
        List<ProductEntity> findAll = productJpaRepository.findAllByIdCategory(idCategory);
        if (!findAll.isEmpty()) {
            return findAll.stream()
                    .map(productEntity -> modelMapper.map(productEntity, ProductDomain.class))
                    .toList();
        }

        return List.of();
    }
}
