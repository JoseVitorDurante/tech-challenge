package br.com.techChallenge.domain.useCases.order;

import br.com.techChallenge.domain.entity.order.OrderDomain;

import java.util.List;

public interface FindAllOrders {

    List<OrderDomain> execute();
}
