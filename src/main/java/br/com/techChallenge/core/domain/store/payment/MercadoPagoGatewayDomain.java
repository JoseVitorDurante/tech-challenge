package br.com.techChallenge.core.domain.store.payment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoGatewayDomain {

    private UUID id;
    private String collectors;
    private String externalPos;
    private String accessToken;
}
