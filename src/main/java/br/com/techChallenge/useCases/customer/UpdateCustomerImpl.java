package br.com.techChallenge.useCases.customer;

import br.com.techChallenge.useCases.customer.exceptions.CustomerNotFound;
import br.com.techChallenge.useCases.store.exceptions.StoreNotFound;
import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.port.customer.CustomerPersistencePort;
import br.com.techChallenge.domain.port.store.StorePersistencePort;
import br.com.techChallenge.domain.useCases.customer.UpdateCustomer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerImpl implements UpdateCustomer {

    private final CustomerPersistencePort customerPersistencePort;
    private final StorePersistencePort storePersistencePort;
    private final ModelMapper modelMapper;
    @Override
    public CustomerDomain execute(CustomerDomain updateCustomerDomain) {
        CustomerDomain domain = customerPersistencePort.findById(updateCustomerDomain.getId())
                .orElseThrow(CustomerNotFound::new);

        storePersistencePort.findById(updateCustomerDomain.getIdStore())
                .orElseThrow(StoreNotFound::new);

        modelMapper.map(updateCustomerDomain, domain);

        return customerPersistencePort.save(domain);
    }
}
