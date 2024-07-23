package br.com.techChallenge.domain.useCases.customer;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;

public interface CreateNewCustomer {

   CustomerDomain execute(CustomerDomain customerDomain);
}
