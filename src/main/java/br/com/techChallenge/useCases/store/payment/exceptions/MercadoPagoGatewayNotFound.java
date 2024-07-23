package br.com.techChallenge.useCases.store.payment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Mercado Pago Payment not exists")
public class MercadoPagoGatewayNotFound extends RuntimeException {

    public MercadoPagoGatewayNotFound() {
        super("Mercado Pago Payment not exists");
    }

    public MercadoPagoGatewayNotFound(String message) {
        super(message);
    }
}
