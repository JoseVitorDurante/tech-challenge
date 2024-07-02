package br.com.techChallenge.core.services.store.payment;

import br.com.techChallenge.core.domain.store.payment.MercadoPagoGatewayDomain;
import br.com.techChallenge.core.exceptions.store.payment.MercadoPagoGatewayNotFound;
import br.com.techChallenge.core.ports.store.payment.MercadoPagoGatewayPersistencePort;
import br.com.techChallenge.core.ports.store.payment.MercadoPagoGatewayServicePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@AllArgsConstructor
public class MercadoPagoGatewayServicePortImpl implements MercadoPagoGatewayServicePort {

    final MercadoPagoGatewayPersistencePort mercadoPagoGatewayPersistencePort;

    final ModelMapper modelMapper;

    @Override
    public MercadoPagoGatewayDomain findById(UUID id) {
        return mercadoPagoGatewayPersistencePort.findById(id)
                .orElseThrow(MercadoPagoGatewayNotFound::new);
    }

    @Override
    public MercadoPagoGatewayDomain save(MercadoPagoGatewayDomain mercadoPagoGatewayDomain) {
        return mercadoPagoGatewayPersistencePort.save(mercadoPagoGatewayDomain);
    }
}
