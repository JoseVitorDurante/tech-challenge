package br.com.techChallenge.adapters.configs;

import br.com.techChallenge.TechChallengeApplication;
import br.com.techChallenge.core.domain.payment.enums.PaymentType;
import br.com.techChallenge.core.ports.category.CategoryPersistencePort;
import br.com.techChallenge.core.ports.customer.CustomerPersistencePort;
import br.com.techChallenge.core.ports.order.OrderPersistencePort;
import br.com.techChallenge.core.ports.order.item.OrderItemPersistencePort;
import br.com.techChallenge.core.ports.payment.PaymentIntegrationPort;
import br.com.techChallenge.core.ports.payment.PaymentPersistencePort;
import br.com.techChallenge.core.ports.product.ProductPersistencePort;
import br.com.techChallenge.core.ports.product.ProductServicePort;
import br.com.techChallenge.core.services.category.CategoryServicePortImpl;
import br.com.techChallenge.core.services.customer.CustomerServicePortImpl;
import br.com.techChallenge.core.services.order.OrderServicePortImpl;
import br.com.techChallenge.core.services.order.item.OrderItemServicePortImpl;
import br.com.techChallenge.core.services.payment.PaymentServiceService;
import br.com.techChallenge.core.services.product.ProductServicePortImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackageClasses = TechChallengeApplication.class)
public class BeanConfiguration {

    @Bean
    public CustomerServicePortImpl customerServicePortImpl(CustomerPersistencePort persistencePort,
                                                           ModelMapper modelMapper) {
        return new CustomerServicePortImpl(persistencePort, modelMapper);
    }

    @Bean
    public ProductServicePort productServicePort(ProductPersistencePort productPersistencePort,
                                                 CategoryPersistencePort categoryPersistencePort,
                                                 ModelMapper modelMapper) {
        return new ProductServicePortImpl(productPersistencePort, categoryPersistencePort, modelMapper);
    }

    @Bean
    public CategoryServicePortImpl categoryServicePortImpl(CategoryPersistencePort categoryPersistencePort,
                                                           ProductPersistencePort productPersistencePort,
                                                           ModelMapper modelMapper) {
        return new CategoryServicePortImpl(categoryPersistencePort, productPersistencePort, modelMapper);
    }

    @Bean
    public OrderServicePortImpl orderServicePortImpl(OrderPersistencePort orderPersistencePort,
                                                     ProductPersistencePort productPersistencePort,
                                                     CustomerPersistencePort persistencePort,
                                                     OrderItemPersistencePort orderItemPersistencePort,
                                                     PaymentServiceService paymentServiceService,
                                                     PaymentPersistencePort paymentPersistencePort,
                                                     ModelMapper modelMapper) {
        return new OrderServicePortImpl(orderPersistencePort, productPersistencePort, persistencePort, orderItemPersistencePort, paymentServiceService, paymentPersistencePort, modelMapper);
    }

    @Bean
    public OrderItemServicePortImpl orderItemServicePortImpl(OrderItemPersistencePort orderItemPersistencePort,
                                                             ModelMapper modelMapper) {
        return new OrderItemServicePortImpl(orderItemPersistencePort, modelMapper);
    }

    @Bean
    public PaymentServiceService paymentService(
            @Qualifier(PaymentType.CIELO_QUALIFIER) PaymentIntegrationPort cieloIntegrationPort,
            @Qualifier(PaymentType.MERCADO_PAGO_QUALIFIER) PaymentIntegrationPort mercadoPagoIntegrationPort,
            PaymentPersistencePort paymentPersistencePort) {

        Map<String, PaymentIntegrationPort> paymentIntegrationPorts = new HashMap<>();
        paymentIntegrationPorts.put(PaymentType.CIELO_QUALIFIER, cieloIntegrationPort);
        paymentIntegrationPorts.put(PaymentType.MERCADO_PAGO_QUALIFIER, mercadoPagoIntegrationPort);
        return new PaymentServiceService(paymentIntegrationPorts, paymentPersistencePort);
    }
}
