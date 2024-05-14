package br.com.techChallenge.adapters.dtos.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInputDTO {

    @Schema(description = "Product name", example = "X-Bacon")
    @NotNull
    @Size(min = 3, max = 60)
    private String name;

    @Schema(description = "Product price", example = "10.99")
    @NotNull
    private BigDecimal price;

    @Schema(description = "Id of the Category this product belongs to", example = "1b4c406d-5bfc-41a3-9259-250cc95fdbb3")
    @NotNull
    private UUID idCategory;

}
