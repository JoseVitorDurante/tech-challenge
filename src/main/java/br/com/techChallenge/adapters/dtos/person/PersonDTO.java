package br.com.techChallenge.adapters.dtos.person;

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

    private UUID id;

    @NotNull
    private String name;

    @CPF
    private String cpf;
}
