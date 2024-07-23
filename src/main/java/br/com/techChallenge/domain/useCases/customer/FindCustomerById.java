package br.com.techChallenge.domain.useCases.customer;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;

import java.util.UUID;

public interface FindCustomerById {

    CustomerDomain execute(UUID id);
}
