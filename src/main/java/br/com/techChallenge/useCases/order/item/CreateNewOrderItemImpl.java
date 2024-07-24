package br.com.techChallenge.useCases.order.item;

import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;
import br.com.techChallenge.domain.persistence.order.item.OrderItemPersistence;
import br.com.techChallenge.domain.useCases.order.item.CreateNewOrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewOrderItemImpl implements CreateNewOrderItem {

    final OrderItemPersistence orderItemPersistence;
    @Override
    public OrderItemDomain execute(OrderItemDomain orderItemDomain) {
        return orderItemPersistence.save(orderItemDomain);
    }
}
