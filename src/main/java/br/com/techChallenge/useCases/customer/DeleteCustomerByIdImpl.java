package br.com.techChallenge.useCases.customer;

import br.com.techChallenge.useCases.customer.exceptions.CustomerNotFound;
import br.com.techChallenge.domain.port.customer.CustomerPersistencePort;
import br.com.techChallenge.domain.useCases.customer.DeleteCustomerById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCustomerByIdImpl implements DeleteCustomerById {

    private final CustomerPersistencePort customerPersistencePort;
    @Override
    public void execute(UUID id) {
        customerPersistencePort.findById(id)
                .orElseThrow(CustomerNotFound::new);
        customerPersistencePort.deleteByID(id);
    }
}
