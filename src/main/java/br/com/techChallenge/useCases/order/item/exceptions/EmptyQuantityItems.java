package br.com.techChallenge.useCases.order.item.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Empty quantity items in order")
public class EmptyQuantityItems extends RuntimeException {

    public EmptyQuantityItems() {
        super("Empty quantity items in order");
    }

}
