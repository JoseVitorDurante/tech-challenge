package br.com.techChallenge.useCases.order;

import br.com.techChallenge.useCases.order.exceptions.OrderNotFound;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.persistence.order.OrderPersistence;
import br.com.techChallenge.domain.useCases.order.FindOrderById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindOrderByIdImpl implements FindOrderById {

    private final OrderPersistence orderPersistence;
    @Override
    public OrderDomain execute(UUID id) {
        return orderPersistence.findById(id)
                .orElseThrow(OrderNotFound::new);
    }
}
