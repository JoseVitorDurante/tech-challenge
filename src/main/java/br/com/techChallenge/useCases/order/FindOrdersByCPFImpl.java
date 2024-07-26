package br.com.techChallenge.useCases.order;

import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.persistence.order.OrderPersistence;
import br.com.techChallenge.domain.useCases.customer.FindCustomerByCPF;
import br.com.techChallenge.domain.useCases.order.FindOrdersByCPF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindOrdersByCPFImpl implements FindOrdersByCPF {

    private final OrderPersistence orderPersistence;
    private final FindCustomerByCPF findCustomerByCPF;
    @Override
    public List<OrderDomain> execute(String cpf) {
        CustomerDomain customerDomain = findCustomerByCPF.execute(cpf);
        return orderPersistence.findByIdCustomer(customerDomain.getId());
    }
}
