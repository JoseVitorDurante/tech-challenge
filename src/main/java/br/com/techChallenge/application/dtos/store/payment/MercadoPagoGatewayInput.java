package br.com.techChallenge.application.dtos.store.payment;

import br.com.techChallenge.domain.entity.payment.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoGatewayInput {

    @Schema(description = "Payment type", example = "MERCADO_PAGO")
    @NotNull
    private PaymentType type = PaymentType.MERCADO_PAGO;

    @Schema(description = "Collectors ID by MercadoPago", example = "1650421194")
    @NotNull
    private String collectors;

    @Schema(description = "External POS ID by MercadoPago", example = "SUC001POS001")
    @NotNull
    private String externalPos;
}
