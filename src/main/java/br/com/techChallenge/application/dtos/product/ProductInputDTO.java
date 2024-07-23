package br.com.techChallenge.application.dtos.product;

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

    @Schema(description = "Id of the Category this product belongs to", example = "d8897bbb-868c-4163-b4c8-2e6baf356683")
    @NotNull
    private UUID idCategory;

    @Schema(description = "Store ID", example = "e1defa99-5e85-4b34-906e-145b1f42bd57")
    @NotNull
    private String idStore;

}
