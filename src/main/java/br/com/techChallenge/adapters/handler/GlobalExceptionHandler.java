package br.com.techChallenge.adapters.handler;

import br.com.techChallenge.core.exceptions.category.ExistProductInCategory;
import br.com.techChallenge.core.exceptions.order.PersonInOrderNotFound;
import br.com.techChallenge.core.exceptions.order.ProductInOrderNotFound;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MESSAGE = "message";
    private static final String ERRORS = "message";

    @ExceptionHandler(ExistProductInCategory.class)
    public ResponseEntity<Object> handleExistProductInCategory(ExistProductInCategory ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(MESSAGE, ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersonInOrderNotFound.class)
    public ResponseEntity<Object> handlePersonInOrderNotFound(PersonInOrderNotFound ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(MESSAGE, ex.getMessage());
        body.put("redirect", "/person");

        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    @ExceptionHandler(ProductInOrderNotFound.class)
    public ResponseEntity<Object> handleProductInOrderNotFound(ProductInOrderNotFound ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(MESSAGE, ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(MESSAGE, "Invalid request content.");

        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList();

        body.put(ERRORS, errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}