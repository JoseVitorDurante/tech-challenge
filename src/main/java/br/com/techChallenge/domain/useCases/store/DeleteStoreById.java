package br.com.techChallenge.domain.useCases.store;

import java.util.UUID;

public interface DeleteStoreById {

    void execute(UUID id);
}
