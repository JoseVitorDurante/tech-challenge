package br.com.techChallenge.useCases.order;

import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.port.order.OrderPersistencePort;
import br.com.techChallenge.domain.useCases.order.FindAllOrders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllOrdersImpl implements FindAllOrders {
    
    private final OrderPersistencePort orderPersistencePort;
    @Override
    public List<OrderDomain> execute() {
        return orderPersistencePort.findAll();
    }
}
