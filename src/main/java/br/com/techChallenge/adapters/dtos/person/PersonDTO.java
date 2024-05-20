package br.com.techChallenge.adapters.dtos.person;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    @Schema(description = "Customer ID", example = "5824b024-e0f7-4eea-a972-dbfd30bb71f3")
    private UUID id;

    @Schema(description = "Customer name", example = "Carolina Herrera")
    @NotNull
    private String name;

    @Schema(description = "Customer name", example = "05385157830")
    @CPF
    private String cpf;

}
