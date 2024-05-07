package br.com.techChallenge.core.services.order.item;

import br.com.techChallenge.core.domain.order.item.OrderItemDomain;
import br.com.techChallenge.core.exceptions.order.OrderNotFound;
import br.com.techChallenge.core.ports.order.item.OrderItemPersistencePort;
import br.com.techChallenge.core.ports.order.item.OrderItemServicePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@AllArgsConstructor
public class OrderItemServicePortImpl implements OrderItemServicePort {

    final OrderItemPersistencePort orderItemPersistencePort;

    final ModelMapper modelMapper;

    @Override
    public OrderItemDomain findById(UUID id) {
        return orderItemPersistencePort.findById(id)
                .orElseThrow(OrderNotFound::new);
    }


    @Override
    public OrderItemDomain save(OrderItemDomain orderItemDomain) {
        return orderItemPersistencePort.save(orderItemDomain);
    }
}
