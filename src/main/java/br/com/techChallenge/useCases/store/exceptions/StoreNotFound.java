package br.com.techChallenge.useCases.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Store not found")
public class StoreNotFound extends RuntimeException {

    public StoreNotFound() {
        super("Store not exists");
    }

    public StoreNotFound(String message) {
        super(message);
    }
}
