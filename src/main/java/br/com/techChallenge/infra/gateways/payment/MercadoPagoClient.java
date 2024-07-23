package br.com.techChallenge.infra.gateways.payment;

import br.com.techChallenge.application.config.FeignConfig;
import br.com.techChallenge.application.dtos.integration.mercadopago.payment.request.MercadoPagoRequest;
import br.com.techChallenge.application.dtos.integration.mercadopago.payment.response.MercadoPagoResponse;
import br.com.techChallenge.application.dtos.integration.mercadopago.payment.response.MerchantOrderResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "mercadoPagoClient",
        url = "${mercado-pago.url}",
        configuration = FeignConfig.class
)
public interface MercadoPagoClient {

    @PostMapping("/instore/orders/qr/seller/collectors/{userId}/pos/{externalPos}/qrs")
    @Headers("Content-Type: application/json")
    MercadoPagoResponse createOrder(
            @RequestHeader("Authorization") String authorization,
            @PathVariable("userId") String userId,
            @PathVariable("externalPos") String externalPos,
            @RequestBody MercadoPagoRequest mercadoPagoRequest
    );

    @GetMapping("/merchant_orders/{orderId}")
    @Headers("Content-Type: application/json")
    MerchantOrderResponse getOrder(
            @RequestHeader("Authorization") String authorization,
            @PathVariable(name = "orderId") Long orderId
    );

}
