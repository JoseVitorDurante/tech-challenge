package br.com.techChallenge.application.dtos.order.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {

    @Schema(description = "Customer ID", example = "53c8910e-bbca-4b3a-af66-e35398478d6e")
    @NotNull
    private UUID id;

    @Schema(description = "Product ID", example = "dd0c4a0c-2309-4466-9352-03e5cc2c8146")
    @NotNull
    private UUID idProduct;

    @Schema(description = "Quantity of items", example = "2")
    @NotNull
    private int quantity;

    @Schema(description = "Item observation", example = "2")
    private String observation;

}
