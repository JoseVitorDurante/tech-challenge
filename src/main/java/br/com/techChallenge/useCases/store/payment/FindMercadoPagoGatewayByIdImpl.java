package br.com.techChallenge.useCases.store.payment;

import br.com.techChallenge.useCases.store.payment.exceptions.MercadoPagoGatewayNotFound;
import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.persistence.store.payment.MercadoPagoGatewayPersistence;
import br.com.techChallenge.domain.useCases.store.payment.FindMercadoPagoGatewayById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindMercadoPagoGatewayByIdImpl implements FindMercadoPagoGatewayById {

    private final MercadoPagoGatewayPersistence mercadoPagoGatewayPersistence;

    @Override
    public DomainEntity execute(UUID id) {
        return mercadoPagoGatewayPersistence.findById(id)
                .orElseThrow(MercadoPagoGatewayNotFound::new);
    }
}
