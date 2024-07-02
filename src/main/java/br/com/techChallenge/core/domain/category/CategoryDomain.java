package br.com.techChallenge.core.domain.category;

import br.com.techChallenge.core.domain.store.StoreDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDomain {
    private UUID id;
    private String name;
    private UUID idStore;
    private StoreDomain store;
}
