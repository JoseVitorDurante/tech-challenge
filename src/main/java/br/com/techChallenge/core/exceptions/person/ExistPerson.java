package br.com.techChallenge.core.exceptions.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Person already exists")
public class ExistPerson extends RuntimeException {

    public ExistPerson() {
        super("Person already exists");
    }
}
