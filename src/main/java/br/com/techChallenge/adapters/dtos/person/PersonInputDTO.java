package br.com.techChallenge.adapters.dtos.person;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInputDTO {

    @NotNull
    private String name;

    @CPF
    private String cpf;
}
