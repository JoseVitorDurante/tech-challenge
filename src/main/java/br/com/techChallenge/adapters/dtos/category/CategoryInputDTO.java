package br.com.techChallenge.adapters.dtos.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInputDTO {

    @Schema(description = "Category name", example = "Dessert")
    @NotNull
    @Size(min = 3, max = 60)
    private String name;

}
