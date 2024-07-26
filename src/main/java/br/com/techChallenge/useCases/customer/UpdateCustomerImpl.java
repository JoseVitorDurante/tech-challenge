package br.com.techChallenge.useCases.customer;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.persistence.customer.CustomerPersistence;
import br.com.techChallenge.domain.useCases.customer.UpdateCustomer;
import br.com.techChallenge.domain.useCases.store.FindStoreById;
import br.com.techChallenge.useCases.customer.exceptions.CustomerNotFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerImpl implements UpdateCustomer {

    private final CustomerPersistence customerPersistencePort;
    private final FindStoreById findStoreById;
    private final ModelMapper modelMapper;

    @Override
    public CustomerDomain execute(CustomerDomain updateCustomerDomain) {
        CustomerDomain domain = customerPersistencePort.findById(updateCustomerDomain.getId())
                .orElseThrow(CustomerNotFound::new);

        findStoreById.execute(updateCustomerDomain.getIdStore());
        modelMapper.map(updateCustomerDomain, domain);

        return customerPersistencePort.save(domain);
    }
}
