package br.com.techChallenge.infra.persistence.entities.order;

import br.com.techChallenge.domain.entity.order.enums.StatusOrder;
import br.com.techChallenge.infra.persistence.entities.customer.CustomerEntity;
import br.com.techChallenge.infra.persistence.entities.order.item.OrderItemEntity;
import br.com.techChallenge.infra.persistence.entities.payment.PaymentEntity;
import br.com.techChallenge.infra.persistence.entities.store.StoreEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrder status;

    @NotNull
    private BigDecimal total;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // ---------------------------------------- RELACIONAMENTOS ----------------------------------

    @Column(name = "ID_STORE", nullable = false)
    private UUID idStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STORE", referencedColumnName = "ID", insertable = false, updatable = false)
    private StoreEntity store;

    @Column(name = "ID_CUSTOMER")
    private UUID idCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUSTOMER", referencedColumnName = "ID", insertable = false, updatable = false)
    private CustomerEntity customer;

    @Column(name = "ID_PAYMENT")
    private UUID idPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAYMENT", referencedColumnName = "ID", insertable = false, updatable = false)
    private PaymentEntity payment;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItemEntity> items = new ArrayList<>();
}
