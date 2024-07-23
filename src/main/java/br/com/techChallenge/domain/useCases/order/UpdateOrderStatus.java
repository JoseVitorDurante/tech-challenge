package br.com.techChallenge.domain.useCases.order;

import br.com.techChallenge.domain.entity.order.enums.StatusOrder;

import java.util.UUID;

public interface UpdateOrderStatus {

    void execute(UUID id, StatusOrder status);
}
