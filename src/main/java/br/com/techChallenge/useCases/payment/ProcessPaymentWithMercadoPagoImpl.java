package br.com.techChallenge.useCases.payment;

import br.com.techChallenge.infra.gateways.payment.MercadoPagoClient;
import br.com.techChallenge.application.dtos.integration.mercadopago.payment.request.CashOutMercadoPago;
import br.com.techChallenge.application.dtos.integration.mercadopago.payment.request.ItemMercadoPago;
import br.com.techChallenge.application.dtos.integration.mercadopago.payment.request.MercadoPagoRequest;
import br.com.techChallenge.application.dtos.integration.mercadopago.payment.response.MercadoPagoResponse;
import br.com.techChallenge.application.dtos.payment.PaymentIntegrationOrder;
import br.com.techChallenge.application.dtos.payment.PaymentIntegrationResult;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;
import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.useCases.payment.ProcessPayment;
import br.com.techChallenge.domain.useCases.store.FindStoreById;
import br.com.techChallenge.infra.persistence.entities.store.StoreEntity;
import br.com.techChallenge.useCases.util.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component(PaymentType.MERCADO_PAGO_QUALIFIER)
public class ProcessPaymentWithMercadoPagoImpl implements ProcessPayment {

    @Value("${mercado-pago.notification-url}")
    private String notificationUrl;

    @Autowired
    private MercadoPagoClient mercadoPagoClient;

    @Autowired
    private FindStoreById findStoreById;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${mercado-pago.access-token}")
    private String accessToken;

    @Override
    public PaymentIntegrationResult processPayment(PaymentIntegrationOrder paymentIntegrationOrder) {
        System.out.println("Processing payment with Mercado Pago");

        MercadoPagoRequest mercadoPagoRequest = new MercadoPagoRequest();
        CashOutMercadoPago cashOutMercadoPago = new CashOutMercadoPago(BigDecimal.ZERO);
        mercadoPagoRequest.setCashOut(cashOutMercadoPago);
        mercadoPagoRequest.setTitle("Tech Challenge");
        mercadoPagoRequest.setDescription("PEDIDOS: " + paymentIntegrationOrder.getOrderPaymentId());
        mercadoPagoRequest.setExpirationDate(DateTimeUtils.generateExpirationDatePayment());
        mercadoPagoRequest.setExternalReference(paymentIntegrationOrder.getOrderPaymentId().toString());
        mercadoPagoRequest.setTotalAmount(paymentIntegrationOrder.getAmount());
        mercadoPagoRequest.setNotificationUrl(notificationUrl);

        paymentIntegrationOrder.getItems().forEach(item -> {
            ItemMercadoPago itemMercadoPago = new ItemMercadoPago();
            itemMercadoPago.setQuantity(item.getQuantity());
            itemMercadoPago.setTotalAmount(item.getTotalAmount());
            itemMercadoPago.setTitle(item.getName());
            itemMercadoPago.setUnitPrice(item.getUnitPrice());
            itemMercadoPago.setUnitMeasure("unit");
            mercadoPagoRequest.getItems().add(itemMercadoPago);
        });

        StoreDomain storeDomain = findStoreById.execute(paymentIntegrationOrder.getIdStore());
        StoreEntity storeEntity = modelMapper.map(storeDomain, StoreEntity.class);


        MercadoPagoResponse paymentMercadoPagoResponse = mercadoPagoClient.createOrder(
                accessToken,
                storeEntity.getMercadoPagoGateway().getCollectors(),
                storeEntity.getMercadoPagoGateway().getExternalPos(),
                mercadoPagoRequest);

        return new PaymentIntegrationResult(paymentIntegrationOrder.getOrderPaymentId(),
                paymentMercadoPagoResponse.getQrData());
    }
}
