package br.com.techChallenge.useCases.customer;

import br.com.techChallenge.useCases.customer.exceptions.ExistCustomer;
import br.com.techChallenge.useCases.store.exceptions.StoreNotFound;
import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.port.customer.CustomerPersistencePort;
import br.com.techChallenge.domain.port.store.StorePersistencePort;
import br.com.techChallenge.domain.useCases.customer.CreateNewCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewCustomerImpl implements CreateNewCustomer {

    private final CustomerPersistencePort customerPersistencePort;

    private final StorePersistencePort storePersistencePort;
    @Override
    public CustomerDomain execute(CustomerDomain customerDomain) {
        customerPersistencePort.findByCpf(customerDomain.getCpf()).ifPresent(pessoa -> {
            throw new ExistCustomer();
        });

        storePersistencePort.findById(customerDomain.getIdStore())
                .orElseThrow(StoreNotFound::new);

        customerDomain.setCpf(customerDomain.getCpf().replaceAll("[^0-9]", ""));

        return customerPersistencePort.save(customerDomain);
    }
}
