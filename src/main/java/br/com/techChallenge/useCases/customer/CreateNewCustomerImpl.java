package br.com.techChallenge.useCases.customer;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.persistence.customer.CustomerPersistence;
import br.com.techChallenge.domain.useCases.customer.CreateNewCustomer;
import br.com.techChallenge.domain.useCases.store.FindStoreById;
import br.com.techChallenge.useCases.customer.exceptions.ExistCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewCustomerImpl implements CreateNewCustomer {

    private final CustomerPersistence customerPersistence;
    private final FindStoreById findStoreById;

    @Override
    public CustomerDomain execute(CustomerDomain customerDomain) {
        customerPersistence.findByCpf(customerDomain.getCpf()).ifPresent(pessoa -> {
            throw new ExistCustomer();
        });

        findStoreById.execute(customerDomain.getIdStore());
        customerDomain.setCpf(customerDomain.getCpf().replaceAll("[^0-9]", ""));

        return customerPersistence.save(customerDomain);
    }
}
