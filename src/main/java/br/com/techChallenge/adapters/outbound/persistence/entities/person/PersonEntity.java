package br.com.techChallenge.adapters.outbound.persistence.entities.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonEntity implements Serializable {

    @Id
    @UuidGenerator
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @CPF
    @NotNull
    @Column(length = 12, unique = true, nullable = false)
    private String cpf;
}
