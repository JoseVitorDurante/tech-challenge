package br.com.techChallenge.adapters.dtos.order.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    @Schema(description = "Product ID", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull
    private UUID idProduct;

    @Schema(description = "Quantity of item", example = "1")
    @NotNull
    @Positive
    private int quantity;

    @Schema(description = "Item observation", example = "Remove the onions and add more cheese.")
    @Size(min = 10, max = 160)
    private String observation;

}
