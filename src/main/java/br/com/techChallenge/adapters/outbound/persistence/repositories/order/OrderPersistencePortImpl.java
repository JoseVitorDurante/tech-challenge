package br.com.techChallenge.adapters.outbound.persistence.repositories.order;


import br.com.techChallenge.adapters.outbound.persistence.entities.order.OrderEntity;
import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.ports.order.OrderPersistencePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class OrderPersistencePortImpl implements OrderPersistencePort {

    private final OrderJpaRepository orderJpaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<OrderDomain> findById(UUID id) {
        return orderJpaRepository.findById(id)
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDomain.class));
    }

    @Override
    public OrderDomain save(OrderDomain orderDomain) {
        OrderEntity map = modelMapper.map(orderDomain, OrderEntity.class);
        OrderEntity orderEntity = orderJpaRepository.save(map);
        return modelMapper.map(orderEntity, OrderDomain.class);
    }

    @Override
    public List<OrderDomain> findAll() {
        List<OrderEntity> findAll = orderJpaRepository.findAll();
        if (!findAll.isEmpty()) {
            return findAll.stream()
                    .map(orderEntity -> modelMapper.map(orderEntity, OrderDomain.class))
                    .toList();
        }

        return List.of();
    }

    @Override
    public List<OrderDomain> findByIdCustomer(UUID idCustomer) {
        List<OrderEntity> findAll = orderJpaRepository.findByIdCustomer(idCustomer);
        if (!findAll.isEmpty()) {
            return findAll.stream()
                    .map(orderEntity -> modelMapper.map(orderEntity, OrderDomain.class))
                    .toList();
        }

        return List.of();
    }
}
