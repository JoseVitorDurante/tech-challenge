package br.com.techChallenge.domain.useCases.customer;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;

import java.util.Optional;

public interface FindCustomerByCPF {

    CustomerDomain execute(String cpf);
}
