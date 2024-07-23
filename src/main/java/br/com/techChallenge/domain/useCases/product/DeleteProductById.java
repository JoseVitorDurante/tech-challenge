package br.com.techChallenge.domain.useCases.product;

import java.util.UUID;

public interface DeleteProductById {

    void execute(UUID id);
}
