package br.com.techChallenge.useCases.order.item;

import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;
import br.com.techChallenge.domain.port.order.item.OrderItemPersistencePort;
import br.com.techChallenge.domain.useCases.order.item.CreateNewOrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewOrderItemImpl implements CreateNewOrderItem {

    final OrderItemPersistencePort orderItemPersistencePort;
    @Override
    public DomainEntity save(OrderItemDomain orderItemDomain) {
        return orderItemPersistencePort.save(orderItemDomain);
    }
}
