package br.com.techChallenge.adapters.dtos.order.response;

import br.com.techChallenge.adapters.dtos.order.request.OrderItemRequest;
import br.com.techChallenge.core.domain.order.enums.StatusOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    @Schema(description = "Order ID", example = "53c8910e-bbca-4b3a-af66-e35398478d6e")
    @NotNull
    private UUID id;

    @Schema(description = "Order ID")
    @NotEmpty
    private List<OrderItemRequest> items = new ArrayList<>();

    @Schema(description = "Order ID", example = "INITIALIZED")
    private StatusOrder status;

    @Schema(description = "Total order", example = "87.80")
    private BigDecimal total;

    @Schema(description = "Customer ID", example = "66b523b6-5d3f-4a94-9eed-249f5e287847")
    private String idPerson;

    @Schema(description = "05385157830")
    private String cpf;
}
