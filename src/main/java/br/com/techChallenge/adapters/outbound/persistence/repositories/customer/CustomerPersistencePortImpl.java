package br.com.techChallenge.adapters.outbound.persistence.repositories.customer;


import br.com.techChallenge.adapters.outbound.persistence.entities.customer.CustomerEntity;
import br.com.techChallenge.core.domain.customer.CustomerDomain;
import br.com.techChallenge.core.ports.customer.CustomerPersistencePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class CustomerPersistencePortImpl implements CustomerPersistencePort {

    private final CustomerJpaRepository customerJpaRepository;
    private final ModelMapper modelMapper;


    @Override
    public Optional<CustomerDomain> findById(UUID id) {
        Optional<CustomerEntity> byId = customerJpaRepository.findById(id);
        return byId.map(customerEntity -> modelMapper.map(customerEntity, CustomerDomain.class));
    }

    @Override
    public CustomerDomain save(CustomerDomain customerDomain) {
        CustomerEntity customerEntity = customerJpaRepository.save(modelMapper.map(customerDomain, CustomerEntity.class));
        return modelMapper.map(customerEntity, CustomerDomain.class);
    }

    @Override
    public List<CustomerDomain> findAll() {
        List<CustomerEntity> findAll = customerJpaRepository.findAll();
        if (!findAll.isEmpty()) {
            return findAll.stream()
                    .map(customerEntity -> modelMapper.map(customerEntity, CustomerDomain.class))
                    .toList();
        }

        return List.of();
    }

    @Override
    public Optional<CustomerDomain> findByCpf(String cpf) {
        Optional<CustomerEntity> byCpf = customerJpaRepository.findByCpf(cpf);
        return byCpf.map(customerEntity -> modelMapper.map(customerEntity, CustomerDomain.class));
    }

    @Override
    public void deleteByID(UUID id) {
        customerJpaRepository.deleteById(id);
    }
}
