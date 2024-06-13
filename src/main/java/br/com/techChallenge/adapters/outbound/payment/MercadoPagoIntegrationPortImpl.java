package br.com.techChallenge.adapters.outbound.payment;

import br.com.techChallenge.adapters.clients.payment.MercadoPagoClient;
import br.com.techChallenge.adapters.dtos.integration.mercadopago.payment.request.CashOutMercadoPago;
import br.com.techChallenge.adapters.dtos.integration.mercadopago.payment.request.ItemMercadoPago;
import br.com.techChallenge.adapters.dtos.integration.mercadopago.payment.request.MercadoPagoRequest;
import br.com.techChallenge.adapters.dtos.integration.mercadopago.payment.response.MercadoPagoResponse;
import br.com.techChallenge.adapters.utils.DateTimeUtils;
import br.com.techChallenge.core.domain.payment.enums.PaymentType;
import br.com.techChallenge.core.dto.payment.PaymentIntegrationOrder;
import br.com.techChallenge.core.dto.payment.PaymentIntegrationResult;
import br.com.techChallenge.core.ports.payment.PaymentIntegrationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component(PaymentType.MERCADO_PAGO_QUALIFIER)
public class MercadoPagoIntegrationPortImpl implements PaymentIntegrationPort {

    @Autowired
    private MercadoPagoClient mercadoPagoClient;

    @Value("${mercadopago.accesstoken}")
    private String accesstoken;

    @Value("${mercadopago.userid}")
    private int userId;

    @Value("${mercadopago.idcaixa}")
    private String idCaixa;

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
        mercadoPagoRequest.setNotificationUrl("https://www.yourserver.com/notifications");

        paymentIntegrationOrder.getItems().forEach(item -> {
            ItemMercadoPago itemMercadoPago = new ItemMercadoPago();
            itemMercadoPago.setQuantity(item.getQuantity());
            itemMercadoPago.setTotalAmount(item.getTotalAmount());
            itemMercadoPago.setTitle(item.getName());
            itemMercadoPago.setUnitPrice(item.getUnitPrice());
            itemMercadoPago.setUnitMeasure("unit");
            mercadoPagoRequest.getItems().add(itemMercadoPago);
        });

        MercadoPagoResponse mercadoPagoResponse = mercadoPagoClient.createOrder(
                accesstoken,
                String.valueOf(userId),
                idCaixa,
                mercadoPagoRequest);

        return new PaymentIntegrationResult(paymentIntegrationOrder.getOrderPaymentId(),
                mercadoPagoResponse.getQrData());
    }
}
