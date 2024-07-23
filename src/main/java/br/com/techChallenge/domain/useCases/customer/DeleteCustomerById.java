package br.com.techChallenge.domain.useCases.customer;

import java.util.UUID;

public interface DeleteCustomerById {

    void execute(UUID id);
}
