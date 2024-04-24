package br.com.techChallenge.adapters.configs;


import br.com.techChallenge.TechChallengeApplication;
import br.com.techChallenge.core.ports.person.PersonPersistencePort;
import br.com.techChallenge.core.services.person.PersonServicePortImpl;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TechChallengeApplication.class)
public class BeanConfiguration {

    @Bean
    PersonServicePortImpl personServicePortImpl(PersonPersistencePort persistencePort, ModelMapper modelMapper) {
        return new PersonServicePortImpl(persistencePort, modelMapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }
}
