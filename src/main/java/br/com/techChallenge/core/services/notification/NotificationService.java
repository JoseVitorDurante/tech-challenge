package br.com.techChallenge.core.services.notification;

import br.com.techChallenge.adapters.clients.payment.MercadoPagoClient;
import br.com.techChallenge.adapters.dtos.integration.mercadopago.payment.response.MerchantOrderResponse;
import br.com.techChallenge.adapters.outbound.persistence.entities.payment.PaymentEntity;
import br.com.techChallenge.adapters.outbound.persistence.repositories.payment.PaymentJpaRepository;
import br.com.techChallenge.core.domain.payment.enums.PaymentStatus;
import br.com.techChallenge.core.exceptions.payment.PaymentNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    final PaymentJpaRepository paymentJpaRepository;

    final MercadoPagoClient mercadoPagoClient;

    @Value("${mercado-pago.access-token}")
    private String accessToken;

    public void updatePaymentStatus(Long merchantOrderId) {
        String formattedAccessToken = String.format("Bearer %s", accessToken);
        MerchantOrderResponse response = mercadoPagoClient.getOrder(formattedAccessToken, merchantOrderId);

        PaymentEntity storedPayment = getPaymentEntity(response.getExternalReference());
        storedPayment.setStatus(PaymentStatus.fromString(response.getStatus()));

        paymentJpaRepository.save(storedPayment);
        log.info("Payment status updated.");
    }

    private PaymentEntity getPaymentEntity(String idPayment) {
        return paymentJpaRepository.findById(UUID.fromString(idPayment))
                .orElseThrow(PaymentNotFound::new);
    }

}
