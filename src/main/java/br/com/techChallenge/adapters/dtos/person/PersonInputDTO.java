package br.com.techChallenge.adapters.dtos.person;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInputDTO {

    @Schema(description = "Customer name", example = "Carolina Herrera")
    @NotBlank
    @Size(min = 3, max = 60)
    private String name;

    @Schema(description = "Customer CPF number", example = "05385157830")
    @CPF
    @NotNull
    private String cpf;

}
