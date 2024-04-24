package br.com.techChallenge.adapters.inbound.controllers.person;

import br.com.techChallenge.adapters.dtos.person.PersonDto;
import br.com.techChallenge.core.domain.person.PersonDomain;
import br.com.techChallenge.core.ports.person.PersonServicePort;
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
@RequestMapping("/pessoa")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PersonController {

    final PersonServicePort personServicePort;
    final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable UUID id) {
        PersonDomain personDomainOptional = personServicePort.findById(id);
        PersonDto personDto = modelMapper.map(personDomainOptional, PersonDto.class);
        return ResponseEntity.ok(personDto);

    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDomain> allPersons = personServicePort.findAll();

        List<PersonDto> allPersonDtos = allPersons.stream()
                .map(person -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());

        if (allPersonDtos.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(allPersonDtos);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PersonDto> findByCpf(@PathVariable String cpf) {
        Optional<PersonDomain> personDomainOptional = personServicePort.findByCpf(cpf);
        if (personDomainOptional.isPresent()) {
            PersonDto personDto = modelMapper.map(personDomainOptional.get(), PersonDto.class);
            return ResponseEntity.ok(personDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonDto> save(@RequestBody PersonDto pessoaDto) {
        PersonDomain domain = modelMapper.map(pessoaDto, PersonDomain.class);
        PersonDomain savedPessoa = personServicePort.save(domain);
        PersonDto dto = modelMapper.map(savedPessoa, PersonDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable UUID id, @RequestBody PersonDto pessoaDto) {
        pessoaDto.setId(id);

        PersonDomain domain = modelMapper.map(pessoaDto, PersonDomain.class);
        PersonDomain updatedPessoa = personServicePort.update(domain);
        PersonDto dto = modelMapper.map(updatedPessoa, PersonDto.class);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        PersonDomain domain = new PersonDomain();
        domain.setId(id);
        personServicePort.delete(domain);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}