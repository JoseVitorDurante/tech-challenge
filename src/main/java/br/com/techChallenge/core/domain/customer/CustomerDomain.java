package br.com.techChallenge.core.domain.customer;

import br.com.techChallenge.core.domain.store.StoreDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDomain {
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private UUID idStore;
    private StoreDomain store;
}
