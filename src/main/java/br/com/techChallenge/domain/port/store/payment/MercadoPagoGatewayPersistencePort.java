package br.com.techChallenge.domain.port.store.payment;

import br.com.techChallenge.domain.entity.store.payment.MercadoPagoGatewayDomain;

import java.util.Optional;
import java.util.UUID;

public interface MercadoPagoGatewayPersistencePort {

    Optional<MercadoPagoGatewayDomain> findById(UUID id);

    MercadoPagoGatewayDomain save(MercadoPagoGatewayDomain mercadoPagoGatewayDomain);
}
