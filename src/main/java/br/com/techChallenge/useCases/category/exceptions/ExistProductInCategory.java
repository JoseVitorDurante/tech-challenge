package br.com.techChallenge.useCases.category.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Category not found")
public class ExistProductInCategory extends RuntimeException {

    public ExistProductInCategory(String message) {
        super(message);
    }
}
