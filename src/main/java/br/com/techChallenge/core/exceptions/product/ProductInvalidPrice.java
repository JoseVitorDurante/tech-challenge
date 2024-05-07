package br.com.techChallenge.core.exceptions.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Price must be greater than zero")
public class ProductInvalidPrice extends RuntimeException {

    public ProductInvalidPrice() {
        super("Price must be greater than zero");
    }
}
