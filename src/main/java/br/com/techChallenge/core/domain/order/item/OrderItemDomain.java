package br.com.techChallenge.core.domain.order.item;

import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.domain.product.ProductDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDomain {
    private UUID id;

    private UUID idProduct;

    private ProductDomain product;

    private UUID idOrder;

    private OrderDomain order;

    private int quantity;

    private String observation;
}
