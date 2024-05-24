package br.com.techChallenge.core.services.customer;

import br.com.techChallenge.core.domain.customer.CustomerDomain;
import br.com.techChallenge.core.exceptions.customer.ExistCustomer;
import br.com.techChallenge.core.exceptions.customer.CustomerNotFound;
import br.com.techChallenge.core.ports.customer.CustomerPersistencePort;
import br.com.techChallenge.core.ports.customer.CustomerServicePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class CustomerServicePortImpl implements CustomerServicePort {

    final CustomerPersistencePort customerPersistencePort;

    final ModelMapper modelMapper;

    @Override
    public CustomerDomain save(CustomerDomain customerDomain) {
        customerPersistencePort.findByCpf(customerDomain.getCpf()).ifPresent(pessoa -> {
            throw new ExistCustomer();
        });

        customerDomain.setCpf(customerDomain.getCpf().replaceAll("[^0-9]", ""));

        return customerPersistencePort.save(customerDomain);
    }

    @Override
    public Optional<CustomerDomain> findByCpf(String cpf) {
        return customerPersistencePort.findByCpf(cpf);
    }

    @Override
    public List<CustomerDomain> findAll() {
        return customerPersistencePort.findAll();
    }

    @Override
    public CustomerDomain findById(UUID id) {
        return customerPersistencePort.findById(id)
                .orElseThrow(CustomerNotFound::new);
    }

    @Override
    public CustomerDomain update(CustomerDomain updateCustomerDomain) {
        CustomerDomain domain = customerPersistencePort.findById(updateCustomerDomain.getId())
                .orElseThrow(CustomerNotFound::new);

        modelMapper.map(updateCustomerDomain, domain);

        return customerPersistencePort.save(domain);
    }

    @Override
    public void delete(CustomerDomain customerDomain) {
        customerPersistencePort.findById(customerDomain.getId())
                .orElseThrow(CustomerNotFound::new);
        customerPersistencePort.delete(customerDomain);
    }
}
