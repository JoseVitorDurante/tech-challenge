package br.com.techChallenge.core.exceptions.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product in order not found")
public class ProductInOrderNotFound extends RuntimeException {

    public ProductInOrderNotFound(String message) {
        super(message);
    }

}
