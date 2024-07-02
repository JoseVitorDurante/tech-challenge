package br.com.techChallenge.adapters.dtos.store;

import br.com.techChallenge.adapters.dtos.store.payment.MercadoPagoGatewayInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreInputDTO {

    @Schema(description = "Category name", example = "Fiap FOOD")
    @NotNull
    @Size(min = 3, max = 60)
    private String name;

    @Schema(description = "Gateway de pagamento do Mercado Pago")
    @Valid
    private MercadoPagoGatewayInput mercadoPagoGateway;

}

