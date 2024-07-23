package br.com.techChallenge.domain.useCases.customer;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;

public interface UpdateCustomer {

    CustomerDomain execute(CustomerDomain updateCustomerDomain);
}
