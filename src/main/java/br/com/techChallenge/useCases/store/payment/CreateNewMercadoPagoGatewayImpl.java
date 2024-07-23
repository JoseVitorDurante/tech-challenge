package br.com.techChallenge.useCases.store.payment;

import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.entity.store.payment.MercadoPagoGatewayDomain;
import br.com.techChallenge.domain.port.store.payment.MercadoPagoGatewayPersistencePort;
import br.com.techChallenge.domain.useCases.store.payment.CreateNewMercadoPagoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewMercadoPagoGatewayImpl implements CreateNewMercadoPagoGateway {

    private final MercadoPagoGatewayPersistencePort mercadoPagoGatewayPersistencePort;
    @Override
    public DomainEntity execute(MercadoPagoGatewayDomain mercadoPagoGatewayDomain) {
        return mercadoPagoGatewayPersistencePort.save(mercadoPagoGatewayDomain);
    }
}
