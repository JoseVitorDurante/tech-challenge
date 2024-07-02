package br.com.techChallenge.core.domain.store;

import br.com.techChallenge.core.domain.store.payment.MercadoPagoGatewayDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDomain {
    private UUID id;

    private String name;

    private boolean active = true;

    private UUID idMercadoPagoGateway;

    private MercadoPagoGatewayDomain mercadoPagoGateway;

}
