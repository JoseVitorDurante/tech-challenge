package br.com.techChallenge.useCases.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Items is empty in order")
public class EmptyOrderItems extends RuntimeException {

    public EmptyOrderItems() {
        super("Items is empty in order");
    }

}
