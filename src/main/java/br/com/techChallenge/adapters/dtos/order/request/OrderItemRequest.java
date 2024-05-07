package br.com.techChallenge.adapters.dtos.order.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    @NotNull
    private UUID idProduct;

    @NotNull
    private int quantity;

    private String observation;
}
