package br.com.techChallenge.adapters.dtos.order.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @Schema(description = "CPF number", example = "58743120059")
    @CPF
    private String cpf;

}
