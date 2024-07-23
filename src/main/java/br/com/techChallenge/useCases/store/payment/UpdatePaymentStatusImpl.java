package br.com.techChallenge.useCases.store.payment;

import br.com.techChallenge.infra.gateways.payment.MercadoPagoClient;
import br.com.techChallenge.application.dtos.integration.mercadopago.payment.response.MerchantOrderResponse;
import br.com.techChallenge.domain.entity.payment.PaymentDomain;
import br.com.techChallenge.domain.entity.payment.enums.PaymentStatus;
import br.com.techChallenge.infra.persistence.repositories.payment.PaymentPersistencePortImpl;
import br.com.techChallenge.domain.useCases.payment.FindPaymentById;
import br.com.techChallenge.domain.useCases.payment.UpdatePaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdatePaymentStatusImpl implements UpdatePaymentStatus {

    private final PaymentPersistencePortImpl paymentPersistencePort;
    private final FindPaymentById findPaymentById;
    private final MercadoPagoClient mercadoPagoClient;

    @Value("${mercado-pago.access-token}")
    private String accessToken;
    @Override
    public void execute(Long merchantOrderId) {

        MerchantOrderResponse response = mercadoPagoClient.getOrder(accessToken, merchantOrderId);
        PaymentDomain paymentDomain = findPaymentById.execute(UUID.fromString(response.getExternalReference()));
        paymentDomain.setStatus(PaymentStatus.fromString(response.getStatus()));

        paymentPersistencePort.save(paymentDomain);
        log.info("Payment status updated.");

    }
}
