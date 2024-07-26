package br.com.techChallenge.useCases.customer;

import br.com.techChallenge.domain.persistence.customer.CustomerPersistence;
import br.com.techChallenge.domain.useCases.customer.DeleteCustomerById;
import br.com.techChallenge.domain.useCases.customer.FindCustomerById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCustomerByIdImpl implements DeleteCustomerById {

    private final CustomerPersistence customerPersistence;
    private final FindCustomerById findCustomerById;

    @Override
    public void execute(UUID id) {
        findCustomerById.execute(id);
        customerPersistence.deleteByID(id);
    }
}
