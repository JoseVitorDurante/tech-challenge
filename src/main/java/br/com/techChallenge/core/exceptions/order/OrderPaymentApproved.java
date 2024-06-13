package br.com.techChallenge.core.exceptions.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Payment already approved")
public class OrderPaymentApproved extends RuntimeException {

    public OrderPaymentApproved() {
        super("Payment already approved");
    }

}
