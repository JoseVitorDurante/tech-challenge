package br.com.techChallenge.domain.useCases.order.item;

import br.com.techChallenge.domain.entity.DomainEntity;

import java.util.UUID;

public interface FindOrderItemById {

    DomainEntity execute(UUID id);
}
