package br.com.techChallenge.adapters.handler;

import br.com.techChallenge.core.exceptions.category.ExistProductInCategory;
import br.com.techChallenge.core.exceptions.order.PersonInOrderNotFound;
import br.com.techChallenge.core.exceptions.order.ProductInOrderNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExistProductInCategory.class)
    public ResponseEntity<Object> handleExistProductInCategory(ExistProductInCategory ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PersonInOrderNotFound.class)
    public ResponseEntity<Object> handlePersonInOrderNotFound(PersonInOrderNotFound ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        body.put("redirect", "/person");

        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    @ExceptionHandler(ProductInOrderNotFound.class)
    public ResponseEntity<Object> handleProductInOrderNotFound(ProductInOrderNotFound ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }
}