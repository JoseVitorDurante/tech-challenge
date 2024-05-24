package br.com.techChallenge.core.exceptions.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer not found")
public class CustomerNotFound extends RuntimeException {

    public CustomerNotFound() {
        super("Customer not found");
    }
}
