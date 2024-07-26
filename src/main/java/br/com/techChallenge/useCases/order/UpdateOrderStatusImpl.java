package br.com.techChallenge.useCases.order;

import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.order.enums.StatusOrder;
import br.com.techChallenge.domain.persistence.order.OrderPersistence;
import br.com.techChallenge.domain.useCases.order.FindOrderById;
import br.com.techChallenge.domain.useCases.order.UpdateOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateOrderStatusImpl implements UpdateOrderStatus {

    private final OrderPersistence orderPersistence;
    private final FindOrderById findOrderById;

    @Override
    public void execute(UUID id, StatusOrder status) {
        OrderDomain orderDomain = findOrderById.execute(id);
        orderDomain.setStatus(status);

        orderPersistence.save(orderDomain);
    }
}
