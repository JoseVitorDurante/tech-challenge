package br.com.techChallenge.adapters.inbound.controllers.person;

import br.com.techChallenge.adapters.dtos.person.PersonDTO;
import br.com.techChallenge.adapters.dtos.person.PersonInputDTO;
import br.com.techChallenge.core.domain.person.PersonDomain;
import br.com.techChallenge.core.ports.person.PersonServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PersonController {

    final PersonServicePort personServicePort;
    final ModelMapper modelMapper;

    @Operation(summary = "Get a person by id")
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable UUID id) {
        PersonDomain personDomainOptional = personServicePort.findById(id);
        PersonDTO personDto = modelMapper.map(personDomainOptional, PersonDTO.class);
        return ResponseEntity.ok(personDto);

    }

    @Operation(summary = "Get all persons")
    @GetMapping("/all")
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<PersonDomain> allPersons = personServicePort.findAll();

        List<PersonDTO> allPersonDTOS = allPersons.stream()
                .map(person -> modelMapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());

        if (allPersonDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(allPersonDTOS);
    }

    @Operation(summary = "Get a person by CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PersonDTO> findByCpf(@PathVariable String cpf) {
        Optional<PersonDomain> personDomainOptional = personServicePort.findByCpf(cpf);
        if (personDomainOptional.isPresent()) {
            PersonDTO personDto = modelMapper.map(personDomainOptional.get(), PersonDTO.class);
            return ResponseEntity.ok(personDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create a new person")
    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody @Valid PersonInputDTO personInputDTO) {
        PersonDomain domain = modelMapper.map(personInputDTO, PersonDomain.class);
        PersonDomain savedPessoa = personServicePort.save(domain);
        PersonDTO dto = modelMapper.map(savedPessoa, PersonDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Update a person by id")
    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable UUID id, @RequestBody PersonInputDTO personInputDTO) {
        PersonDomain domain = modelMapper.map(personInputDTO, PersonDomain.class);
        domain.setId(id);

        PersonDomain updatedPessoa = personServicePort.update(domain);
        PersonDTO dto = modelMapper.map(updatedPessoa, PersonDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Delete a person by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        PersonDomain domain = new PersonDomain();
        domain.setId(id);
        personServicePort.delete(domain);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}