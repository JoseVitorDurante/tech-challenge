package br.com.techChallenge.adapters.configs;

import br.com.techChallenge.adapters.dtos.order.response.OrderResponse;
import br.com.techChallenge.core.domain.order.OrderDomain;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.addMappings(new PropertyMap<OrderDomain, OrderResponse>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map(source.getCustomer().getCpf(), destination.getCpf());
                when(Conditions.isNotNull()).map(source.getPayment().getId(), destination.getIdPayment());
                when(Conditions.isNotNull()).map(source.getPayment().getQrCode(), destination.getQrCode());
                when(Conditions.isNotNull()).map(source.getPayment().getStatus(), destination.getPaymentStatus());
            }
        });

        return modelMapper;
    }

}
