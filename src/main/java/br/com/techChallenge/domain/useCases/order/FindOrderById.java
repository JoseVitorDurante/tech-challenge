package br.com.techChallenge.domain.useCases.order;

import br.com.techChallenge.domain.entity.order.OrderDomain;

import java.util.UUID;

public interface FindOrderById {

    OrderDomain execute(UUID id);
}
