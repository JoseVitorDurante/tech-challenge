package br.com.techChallenge.core.ports.payment;

import br.com.techChallenge.core.domain.payment.PaymentDomain;

import java.util.Optional;
import java.util.UUID;

public interface PaymentPersistencePort {

    PaymentDomain save(PaymentDomain paymentDomain);

    void delete(UUID id);

    Optional<PaymentDomain> findById(UUID idPayment);
}
