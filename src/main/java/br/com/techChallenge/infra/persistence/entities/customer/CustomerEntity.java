package br.com.techChallenge.infra.persistence.entities.customer;

import br.com.techChallenge.infra.persistence.entities.store.StoreEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerEntity implements Serializable {

    @Id
    @UuidGenerator
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String email;

    @CPF
    @NotNull
    @Column(length = 12, unique = true, nullable = false)
    private String cpf;

    // ---------------------------------------- RELACIONAMENTOS ----------------------------------
    @Column(name = "ID_STORE", nullable = false)
    private UUID idStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STORE", referencedColumnName = "ID", insertable = false, updatable = false)
    private StoreEntity store;
}
