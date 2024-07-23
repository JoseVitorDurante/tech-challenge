package br.com.techChallenge.useCases.order;

import br.com.techChallenge.useCases.order.exceptions.OrderNotFound;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.order.enums.StatusOrder;
import br.com.techChallenge.domain.port.order.OrderPersistencePort;
import br.com.techChallenge.domain.useCases.order.UpdateOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateOrderStatusImpl implements UpdateOrderStatus {

    private final OrderPersistencePort orderPersistencePort;
    @Override
    public void execute(UUID id, StatusOrder status) {
        OrderDomain orderDomain = orderPersistencePort.findById(id)
                .orElseThrow(OrderNotFound::new);

        orderDomain.setStatus(status);

        orderPersistencePort.save(orderDomain);
    }
}
