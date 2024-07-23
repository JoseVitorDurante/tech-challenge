package br.com.techChallenge.useCases.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer in order not found")
public class CustomerInOrderNotFound extends RuntimeException {

    public CustomerInOrderNotFound(String message) {
        super(message);
    }

}
