package br.com.techChallenge.domain.useCases.customer;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;

import java.util.List;

public interface FindAllCustomers {

    List<CustomerDomain> execute();
}
