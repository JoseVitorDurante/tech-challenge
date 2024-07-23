package br.com.techChallenge.useCases.customer;

import br.com.techChallenge.useCases.customer.exceptions.CustomerNotFoundByCPF;
import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.port.customer.CustomerPersistencePort;
import br.com.techChallenge.domain.useCases.customer.FindCustomerByCPF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindCustomerByCPFImpl implements FindCustomerByCPF {

    private final CustomerPersistencePort customerPersistencePort;
    @Override
    public CustomerDomain execute(String cpf) {
        return customerPersistencePort.findByCpf(cpf)
                .orElseThrow(CustomerNotFoundByCPF::new);
    }
}
