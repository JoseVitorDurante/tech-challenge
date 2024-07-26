package br.com.techChallenge.useCases.customer;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.persistence.customer.CustomerPersistence;
import br.com.techChallenge.domain.useCases.customer.FindAllCustomers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllCustomersImpl implements FindAllCustomers {

    private final CustomerPersistence customerPersistencePort;
    @Override
    public List<CustomerDomain> execute() {
        return customerPersistencePort.findAll();
    }
}
