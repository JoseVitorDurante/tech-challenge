package br.com.techChallenge.domain.useCases.store.payment;

import br.com.techChallenge.domain.entity.DomainEntity;

import java.util.UUID;

public interface FindMercadoPagoGatewayById {

    DomainEntity execute(UUID id);
}
