package br.com.techChallenge.domain.useCases.category;

import java.util.UUID;

public interface DeleteCategoryById {

    void execute(UUID id);
}
