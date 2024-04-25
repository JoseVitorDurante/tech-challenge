package br.com.techChallenge.adapters.configs;


import br.com.techChallenge.TechChallengeApplication;
import br.com.techChallenge.core.ports.category.CategoryPersistencePort;
import br.com.techChallenge.core.ports.person.PersonPersistencePort;
import br.com.techChallenge.core.ports.product.ProductPersistencePort;
import br.com.techChallenge.core.ports.product.ProductServicePort;
import br.com.techChallenge.core.services.category.CategoryServicePortImpl;
import br.com.techChallenge.core.services.person.PersonServicePortImpl;
import br.com.techChallenge.core.services.product.ProductServicePortImpl;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TechChallengeApplication.class)
public class BeanConfiguration {

    @Bean
    public PersonServicePortImpl personServicePortImpl(PersonPersistencePort persistencePort, ModelMapper modelMapper) {
        return new PersonServicePortImpl(persistencePort, modelMapper);
    }

    @Bean
    public ProductServicePort productServicePort(ProductPersistencePort productPersistencePort, CategoryPersistencePort categoryPersistencePort,ModelMapper modelMapper) {
        return new ProductServicePortImpl(productPersistencePort, categoryPersistencePort, modelMapper);
    }

    @Bean
    public CategoryServicePortImpl categoryServicePortImpl(CategoryPersistencePort categoryPersistencePort, ProductPersistencePort productPersistencePort, ModelMapper modelMapper) {
        return new CategoryServicePortImpl(categoryPersistencePort, productPersistencePort, modelMapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }
}
