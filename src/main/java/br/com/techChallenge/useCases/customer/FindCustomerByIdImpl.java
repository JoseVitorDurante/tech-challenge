package br.com.techChallenge.useCases.customer;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.useCases.customer.exceptions.CustomerNotFound;
import br.com.techChallenge.domain.port.customer.CustomerPersistencePort;
import br.com.techChallenge.domain.useCases.customer.FindCustomerById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindCustomerByIdImpl implements FindCustomerById {

    private final CustomerPersistencePort customerPersistencePort;
    @Override
    public CustomerDomain execute(UUID id) {
        return customerPersistencePort.findById(id)
                .orElseThrow(CustomerNotFound::new);
    }
}
