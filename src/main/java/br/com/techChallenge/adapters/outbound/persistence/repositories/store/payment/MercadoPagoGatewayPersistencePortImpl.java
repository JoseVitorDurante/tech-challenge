package br.com.techChallenge.adapters.outbound.persistence.repositories.store.payment;


import br.com.techChallenge.adapters.outbound.persistence.entities.store.payment.MercadoPagoGatewayEntity;
import br.com.techChallenge.core.domain.store.payment.MercadoPagoGatewayDomain;
import br.com.techChallenge.core.ports.store.payment.MercadoPagoGatewayPersistencePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class MercadoPagoGatewayPersistencePortImpl implements MercadoPagoGatewayPersistencePort {

    private final MercadoPagoGatewayJpaRepository mercadoPagoGatewayJpaRepository;
    private final ModelMapper modelMapper;


    @Override
    public MercadoPagoGatewayDomain save(MercadoPagoGatewayDomain mercadoPagoGatewayDomain) {
        MercadoPagoGatewayEntity map = modelMapper.map(mercadoPagoGatewayDomain, MercadoPagoGatewayEntity.class);
        MercadoPagoGatewayEntity mercadoPagoGateway = mercadoPagoGatewayJpaRepository.save(map);
        return modelMapper.map(mercadoPagoGateway, MercadoPagoGatewayDomain.class);
    }

    @Override
    public Optional<MercadoPagoGatewayDomain> findById(UUID id) {
        return mercadoPagoGatewayJpaRepository.findById(id)
                .map(storeEntity -> modelMapper.map(storeEntity, MercadoPagoGatewayDomain.class));
    }
}
