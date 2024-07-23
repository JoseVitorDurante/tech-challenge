package br.com.techChallenge.domain.entity.payment;

import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.entity.payment.enums.PaymentStatus;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDomain extends DomainEntity {

    private UUID id;

    private UUID idOrder;

    private BigDecimal amount;

    private PaymentType type;

    private String qrCode;

    private PaymentStatus status;
}
