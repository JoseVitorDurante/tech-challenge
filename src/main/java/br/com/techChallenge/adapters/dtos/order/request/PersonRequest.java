package br.com.techChallenge.adapters.dtos.order.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

    @Schema(description = "Email address", example = "carolina.herrera@icloud.com")
    @Email
    @Size(min = 3, max = 80)
    private String email;

    @Schema(description = "CPF number", example = "05385157830")
    @CPF
    private String cpf;

}
