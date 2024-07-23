package br.com.techChallenge.domain.useCases.store.payment;

import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.entity.store.payment.MercadoPagoGatewayDomain;

public interface CreateNewMercadoPagoGateway {

    DomainEntity execute(MercadoPagoGatewayDomain mercadoPagoGatewayDomain);
}
