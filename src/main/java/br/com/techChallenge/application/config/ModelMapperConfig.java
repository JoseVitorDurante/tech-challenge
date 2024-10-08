package br.com.techChallenge.application.config;

import br.com.techChallenge.application.dtos.order.response.OrderResponse;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<OrderDomain, OrderResponse>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map(source.getCustomer().getCpf(), destination.getCpf());
                when(Conditions.isNotNull()).map(source.getPayment().getId(), destination.getIdPayment());
                when(Conditions.isNotNull()).map(source.getPayment().getQrCode(), destination.getQrCode());
                when(Conditions.isNotNull()).map(source.getPayment().getStatus(), destination.getPaymentStatus());
                when(Conditions.isNotNull()).map(source.getPayment().getType(), destination.getPaymentType());
            }
        });

        return modelMapper;
    }

}
