package br.com.techChallenge.core.domain.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDomain {
    private UUID id;
    private String nome;
    private String cpf;
}
