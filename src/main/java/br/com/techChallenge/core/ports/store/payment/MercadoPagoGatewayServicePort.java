package br.com.techChallenge.core.ports.store.payment;

import br.com.techChallenge.core.domain.store.payment.MercadoPagoGatewayDomain;

import java.util.UUID;

public interface MercadoPagoGatewayServicePort {

    MercadoPagoGatewayDomain findById(UUID id);

    MercadoPagoGatewayDomain save(MercadoPagoGatewayDomain mercadoPagoGatewayDomain);
}
