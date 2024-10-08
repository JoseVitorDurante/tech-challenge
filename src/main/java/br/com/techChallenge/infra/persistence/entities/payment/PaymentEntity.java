package br.com.techChallenge.infra.persistence.entities.payment;

import br.com.techChallenge.domain.entity.payment.enums.PaymentStatus;
import br.com.techChallenge.domain.entity.payment.enums.PaymentType;
import br.com.techChallenge.infra.persistence.entities.order.OrderEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentEntity {

    @Id
    private UUID id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String qrCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // ---------------------------------------- RELACIONAMENTOS ----------------------------------

    @Column(name = "ID_ORDER", nullable = false)
    private UUID idOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ORDER", referencedColumnName = "ID", insertable = false, updatable = false)
    private OrderEntity order;
}
