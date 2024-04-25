package br.com.techChallenge.adapters.outbound.persistence.repositories.person;


import br.com.techChallenge.adapters.outbound.persistence.entities.person.PersonEntity;
import br.com.techChallenge.core.domain.person.PersonDomain;
import br.com.techChallenge.core.ports.person.PersonPersistencePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class PersonPersistencePortImpl implements PersonPersistencePort {

    private final PersonJpaRepository personJpaRepository;
    private final ModelMapper modelMapper;


    @Override
    public Optional<PersonDomain> findById(UUID id) {
        Optional<PersonEntity> byId = personJpaRepository.findById(id);
        return byId.map(personEntity -> modelMapper.map(personEntity, PersonDomain.class));
    }

    @Override
    public PersonDomain save(PersonDomain personDomain) {
        PersonEntity personEntity = personJpaRepository.save(modelMapper.map(personDomain, PersonEntity.class));
        return modelMapper.map(personEntity, PersonDomain.class);
    }

    @Override
    public List<PersonDomain> findAll() {
        List<PersonEntity> findAll = personJpaRepository.findAll();
        if (!findAll.isEmpty()) {
            return findAll.stream()
                    .map(personEntity -> modelMapper.map(personEntity, PersonDomain.class))
                    .toList();
        }

        return List.of();
    }

    @Override
    public Optional<PersonDomain> findByCpf(String cpf) {
        Optional<PersonEntity> byCpf = personJpaRepository.findByCpf(cpf);
        return byCpf.map(personEntity -> modelMapper.map(personEntity, PersonDomain.class));
    }

    @Override
    public void delete(PersonDomain personDomain) {
        personJpaRepository.delete(modelMapper.map(personDomain, PersonEntity.class));
    }


}
