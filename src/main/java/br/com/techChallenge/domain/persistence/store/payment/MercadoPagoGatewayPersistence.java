package br.com.techChallenge.domain.persistence.store.payment;

import br.com.techChallenge.domain.entity.store.payment.MercadoPagoGatewayDomain;

import java.util.Optional;
import java.util.UUID;

public interface MercadoPagoGatewayPersistence {

    Optional<MercadoPagoGatewayDomain> findById(UUID id);

    MercadoPagoGatewayDomain save(MercadoPagoGatewayDomain mercadoPagoGatewayDomain);
}
