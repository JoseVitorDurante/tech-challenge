package br.com.techChallenge.adapters.dtos.store.payment;

import br.com.techChallenge.core.domain.payment.enums.PaymentType;
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

    @Schema(description = "Access token by MercadoPago", example = "APP_USR-151362775144770-012219-ad26be97b5a2ceab5965aaf6409a0d70-1650421194")
    @NotNull
    private String accessToken;
}
