package br.com.techChallenge.core.exceptions.store;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Store inactive")
public class StoreInactive extends RuntimeException {

    public StoreInactive() {
        super("Store inactive");
    }

    public StoreInactive(String message) {
        super(message);
    }
}
