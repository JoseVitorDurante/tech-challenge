package br.com.techChallenge.adapters.outbound.persistence.repositories.payment;


import br.com.techChallenge.adapters.outbound.persistence.entities.payment.PaymentEntity;
import br.com.techChallenge.core.domain.payment.PaymentDomain;
import br.com.techChallenge.core.ports.payment.PaymentPersistencePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class PaymentPersistencePortImpl implements PaymentPersistencePort {

    private final PaymentJpaRepository productJpaRepository;
    private final ModelMapper modelMapper;

    @Override
    public PaymentDomain save(PaymentDomain paymentDomain) {
        PaymentEntity map = modelMapper.map(paymentDomain, PaymentEntity.class);
        PaymentEntity paymentEntity = productJpaRepository.save(map);
        return modelMapper.map(paymentEntity, PaymentDomain.class);
    }

    @Override
    public void deleteByID(UUID id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    public Optional<PaymentDomain> findById(UUID idPayment) {
        return productJpaRepository.findById(idPayment)
                .map(paymentEntity -> modelMapper.map(paymentEntity, PaymentDomain.class));
    }
}
