package br.com.techChallenge.adapters.outbound.persistence.entities.store;

import br.com.techChallenge.adapters.outbound.persistence.entities.store.payment.MercadoPagoGatewayEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "stores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreEntity implements Serializable {

    @Id
    @UuidGenerator
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private boolean active = true;

    // ---------------------------------------- RELACIONAMENTOS ----------------------------------

    @Column(name = "ID_MERCADO_PAGO_GATEWAY")
    private UUID idMercadoPagoGateway;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_MERCADO_PAGO_GATEWAY", referencedColumnName = "ID", insertable = false, updatable = false)
    private MercadoPagoGatewayEntity mercadoPagoGateway;
}
