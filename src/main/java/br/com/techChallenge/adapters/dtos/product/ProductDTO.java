package br.com.techChallenge.adapters.dtos.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @Schema(description = "Product ID", example = "53c8910e-bbca-4b3a-af66-e35398478d6e")
    private UUID id;

    @Schema(description = "Product name", example = "X-Bacon")
    @NotNull
    private String name;

    @Schema(description = "Product price", example = "31.99")
    @NotNull
    private BigDecimal price;

    @Schema(description = "Id of the Category this product belongs to", example = "b51da555-7434-4235-a085-fd5e6e11633d")
    @NotNull
    private UUID idCategory;

}
