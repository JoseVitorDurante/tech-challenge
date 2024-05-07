package br.com.techChallenge.core.domain.order;

import br.com.techChallenge.core.domain.order.enums.StatusOrder;
import br.com.techChallenge.core.domain.order.item.OrderItemDomain;
import br.com.techChallenge.core.domain.person.PersonDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDomain {
    private UUID id;

    private List<OrderItemDomain> items;

    private StatusOrder status;

    private BigDecimal total;

    private UUID idPerson;

    private PersonDomain person;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
