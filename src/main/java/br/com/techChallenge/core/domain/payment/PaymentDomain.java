package br.com.techChallenge.core.domain.payment;

import br.com.techChallenge.core.domain.payment.enums.PaymentStatus;
import br.com.techChallenge.core.domain.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDomain {

    private UUID id;

    private UUID idOrder;

    private BigDecimal amount;

    private PaymentType type;

    private String qrCode;

    private PaymentStatus status;
}
