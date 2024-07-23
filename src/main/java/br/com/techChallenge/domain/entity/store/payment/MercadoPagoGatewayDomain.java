package br.com.techChallenge.domain.entity.store.payment;


import br.com.techChallenge.domain.entity.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoGatewayDomain extends DomainEntity {

    private String collectors;
    private String externalPos;
}
