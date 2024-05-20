package br.com.techChallenge.adapters.dtos.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @Schema(description = "Category ID", example = "d8897bbb-868c-4163-b4c8-2e6baf356683")
    private UUID id;

    @Schema(description = "Category ID", example = "Dessert")
    @NotNull
    private String name;

}
