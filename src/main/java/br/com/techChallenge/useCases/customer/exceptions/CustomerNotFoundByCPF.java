package br.com.techChallenge.useCases.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer not found")
public class CustomerNotFoundByCPF extends RuntimeException{

    public CustomerNotFoundByCPF() {
        super("The customer with the provided CPF was not found");
    }
}
