package br.com.techChallenge.core.ports.person;

import br.com.techChallenge.core.domain.person.PersonDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonPersistencePort {

    Optional<PersonDomain> findById(UUID id);

    PersonDomain save(PersonDomain personDomain);

    Optional<PersonDomain> findByCpf(String cpf);

    PersonDomain update(PersonDomain personDomain);

    void delete(PersonDomain personDomain);

    List<PersonDomain> findAll();
}
