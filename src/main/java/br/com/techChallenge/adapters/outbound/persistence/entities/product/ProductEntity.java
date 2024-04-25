package br.com.techChallenge.adapters.outbound.persistence.entities.product;

import br.com.techChallenge.adapters.outbound.persistence.entities.category.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductEntity implements Serializable {

    @Id
    @UuidGenerator
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    // ---------------------------------------- RELACIONAMENTOS ----------------------------------

    @Column(name = "ID_CATEGORY", nullable = false)
    private UUID idCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORY", referencedColumnName = "ID", insertable = false, updatable = false)
    private CategoryEntity category;
}
