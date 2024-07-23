package br.com.techChallenge.domain.entity.order.item;

import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.product.ProductDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDomain extends DomainEntity {

    private UUID idProduct;

    private ProductDomain product;

    private UUID idOrder;

    private OrderDomain order;

    private int quantity;

    private String observation;
}
