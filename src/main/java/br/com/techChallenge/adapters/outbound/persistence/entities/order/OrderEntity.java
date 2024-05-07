package br.com.techChallenge.adapters.outbound.persistence.entities.order;

import br.com.techChallenge.adapters.outbound.persistence.entities.order.enums.StatusOrder;
import br.com.techChallenge.adapters.outbound.persistence.entities.order.item.OrderItemEntity;
import br.com.techChallenge.adapters.outbound.persistence.entities.person.PersonEntity;
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

    @Column(name = "ID_PERSON", nullable = true)
    private UUID idPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSON", referencedColumnName = "ID", insertable = false, updatable = false)
    private PersonEntity person;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItemEntity> items = new ArrayList<>();
}
