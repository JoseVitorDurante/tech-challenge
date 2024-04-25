package br.com.techChallenge.core.services.person;

import br.com.techChallenge.core.domain.person.PersonDomain;
import br.com.techChallenge.core.exceptions.person.ExistPerson;
import br.com.techChallenge.core.exceptions.person.PersonNotFound;
import br.com.techChallenge.core.ports.person.PersonPersistencePort;
import br.com.techChallenge.core.ports.person.PersonServicePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class PersonServicePortImpl implements PersonServicePort {

    final PersonPersistencePort personPersistencePort;

    final ModelMapper modelMapper;

    @Override
    public PersonDomain save(PersonDomain personDomain) {
        personPersistencePort.findByCpf(personDomain.getCpf()).ifPresent(pessoa -> {
            throw new ExistPerson();
        });

        personDomain.setCpf(personDomain.getCpf().replaceAll("[^0-9]", ""));

        return personPersistencePort.save(personDomain);
    }

    @Override
    public Optional<PersonDomain> findByCpf(String cpf) {
        return personPersistencePort.findByCpf(cpf);
    }

    @Override
    public List<PersonDomain> findAll() {
        return personPersistencePort.findAll();
    }

    @Override
    public PersonDomain findById(UUID id) {
        return personPersistencePort.findById(id)
                .orElseThrow(PersonNotFound::new);
    }

    @Override
    public PersonDomain update(PersonDomain updatePersonDomain) {
        PersonDomain domain = personPersistencePort.findById(updatePersonDomain.getId())
                .orElseThrow(PersonNotFound::new);

        modelMapper.map(updatePersonDomain, domain);

        return personPersistencePort.save(domain);
    }

    @Override
    public void delete(PersonDomain personDomain) {
        personPersistencePort.findById(personDomain.getId())
                .orElseThrow(PersonNotFound::new);
        personPersistencePort.delete(personDomain);
    }
}
