package br.com.techChallenge.useCases.order;

import br.com.techChallenge.useCases.order.exceptions.OrderNotFound;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.port.order.OrderPersistencePort;
import br.com.techChallenge.domain.useCases.order.FindOrderById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindOrderByIdImpl implements FindOrderById {

    private final OrderPersistencePort orderPersistencePort;
    @Override
    public OrderDomain execute(UUID id) {
        return orderPersistencePort.findById(id)
                .orElseThrow(OrderNotFound::new);
    }
}
