package br.com.techChallenge.domain.port.payment;

import br.com.techChallenge.domain.entity.payment.PaymentDomain;

import java.util.Optional;
import java.util.UUID;

public interface PaymentPersistencePort {

    PaymentDomain save(PaymentDomain paymentDomain);

    void deleteByID(UUID id);

    Optional<PaymentDomain> findById(UUID idPayment);
}
