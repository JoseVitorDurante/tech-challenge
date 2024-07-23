package br.com.techChallenge.useCases.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found")
public class ProductNotFound extends RuntimeException {

    public ProductNotFound() {
        super("Product not exists");
    }

    public ProductNotFound(String message) {
        super(message);
    }
}
