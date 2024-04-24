package br.com.techChallenge.core.exceptions.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Person not found")
public class PersonNotFound extends RuntimeException {

    public PersonNotFound() {
        super("Person not found");
    }
}
