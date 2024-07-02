package br.com.techChallenge.core.ports.customer;

import br.com.techChallenge.core.domain.customer.CustomerDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerPersistencePort {

    Optional<CustomerDomain> findById(UUID id);

    CustomerDomain save(CustomerDomain customerDomain);

    Optional<CustomerDomain> findByCpf(String cpf);

    void deleteByID(UUID id);

    List<CustomerDomain> findAll();
}
