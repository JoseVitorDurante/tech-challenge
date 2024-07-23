package br.com.techChallenge.useCases.order.item;

import br.com.techChallenge.useCases.order.exceptions.OrderNotFound;
import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.port.order.item.OrderItemPersistencePort;
import br.com.techChallenge.domain.useCases.order.item.FindOrderItemById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindOrderItemByIdImpl implements FindOrderItemById {

    private final OrderItemPersistencePort orderItemPersistencePort;
    @Override
    public DomainEntity execute(UUID id) {
        return orderItemPersistencePort.findById(id)
                .orElseThrow(OrderNotFound::new);
    }
}
