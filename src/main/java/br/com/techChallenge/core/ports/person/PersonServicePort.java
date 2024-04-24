package br.com.techChallenge.core.ports.person;

import br.com.techChallenge.core.domain.person.PersonDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonServicePort {

    PersonDomain findById(UUID id);

    List<PersonDomain> findAll();

    PersonDomain save(PersonDomain personDomain);

    Optional<PersonDomain> findByCpf(String cpf);

    PersonDomain update(PersonDomain personDomain);

    void delete(PersonDomain personDomain);


}
