package br.com.techChallenge.application.dtos.order.request;

import br.com.techChallenge.domain.entity.payment.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private List<OrderItemRequest> items = new ArrayList<>();

    @Schema(description = "Store ID", example = "e1defa99-5e85-4b34-906e-145b1f42bd57")
    @NotNull
    private UUID idStore;

    @Schema(description = "Type payment", example = "MERCADO_PAGO")
    @NotNull
    private PaymentType paymentType;

    private CustomerRequest customer;
}
