package br.com.techChallenge.useCases.store.payment;

import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.entity.store.payment.MercadoPagoGatewayDomain;
import br.com.techChallenge.domain.persistence.store.payment.MercadoPagoGatewayPersistence;
import br.com.techChallenge.domain.useCases.store.payment.CreateNewMercadoPagoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewMercadoPagoGatewayImpl implements CreateNewMercadoPagoGateway {

    private final MercadoPagoGatewayPersistence mercadoPagoGatewayPersistence;
    @Override
    public DomainEntity execute(MercadoPagoGatewayDomain mercadoPagoGatewayDomain) {
        return mercadoPagoGatewayPersistence.save(mercadoPagoGatewayDomain);
    }
}
