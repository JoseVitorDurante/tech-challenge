package br.com.techChallenge.core.ports.store.payment;

import br.com.techChallenge.core.domain.store.payment.MercadoPagoGatewayDomain;

import java.util.Optional;
import java.util.UUID;

public interface MercadoPagoGatewayPersistencePort {

    Optional<MercadoPagoGatewayDomain> findById(UUID id);

    MercadoPagoGatewayDomain save(MercadoPagoGatewayDomain mercadoPagoGatewayDomain);
}
