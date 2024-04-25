package br.com.techChallenge.adapters.dtos.product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInputDTO {

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private UUID idCategory;
}
