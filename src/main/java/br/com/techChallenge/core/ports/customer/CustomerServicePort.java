package br.com.techChallenge.core.ports.customer;

import br.com.techChallenge.core.domain.customer.CustomerDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerServicePort {

    CustomerDomain findById(UUID id);

    List<CustomerDomain> findAll();

    CustomerDomain save(CustomerDomain customerDomain);

    Optional<CustomerDomain> findByCpf(String cpf);

    CustomerDomain update(CustomerDomain updateCustomerDomain);

    void delete(CustomerDomain customerDomain);
}
