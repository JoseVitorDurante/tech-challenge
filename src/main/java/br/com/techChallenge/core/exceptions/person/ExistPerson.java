package br.com.techChallenge.core.exceptions.person;

public class ExistPerson extends RuntimeException {

    public ExistPerson() {
        super("Person already exists");
    }
}
