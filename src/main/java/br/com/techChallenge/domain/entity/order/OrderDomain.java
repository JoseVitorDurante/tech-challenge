package br.com.techChallenge.domain.entity.order;

import br.com.techChallenge.useCases.order.exceptions.EmptyOrderItems;
import br.com.techChallenge.useCases.order.exceptions.OrderPaymentApproved;
import br.com.techChallenge.useCases.order.exceptions.ProductInOrderNotFound;
import br.com.techChallenge.useCases.order.item.exceptions.EmptyQuantityItems;
import br.com.techChallenge.useCases.product.exceptions.ProductNotFound;
import br.com.techChallenge.useCases.store.exceptions.StoreInactive;
import br.com.techChallenge.useCases.store.exceptions.StoreNotFound;
import br.com.techChallenge.domain.entity.DomainEntity;
import br.com.techChallenge.domain.entity.customer.CustomerDomain;
import br.com.techChallenge.domain.entity.order.enums.StatusOrder;
import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;
import br.com.techChallenge.domain.entity.payment.PaymentDomain;
import br.com.techChallenge.domain.entity.payment.enums.PaymentStatus;
import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.port.product.ProductPersistencePort;
import br.com.techChallenge.domain.port.store.StorePersistencePort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDomain extends DomainEntity {

    private List<OrderItemDomain> items;
    private StatusOrder status;
    private BigDecimal total;
    private UUID idCustomer;
    private CustomerDomain customer;
    private UUID idStore;
    private StoreDomain store;
    private UUID idPayment;
    private PaymentDomain payment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void validatedStore(StorePersistencePort storePersistencePort) {
        StoreDomain storeDomain = storePersistencePort.findById(this.getIdStore())
                .orElseThrow(StoreNotFound::new);

        if (!storeDomain.isActive())
            throw new StoreInactive();

        this.setStore(storeDomain);
        this.setIdStore(storeDomain.getId());
    }

    public void validatedQuantityItems(List<OrderItemDomain> items) {
        if (items.isEmpty()) {
            throw new EmptyOrderItems();
        }

        if (items.stream().anyMatch(item -> item.getQuantity() == 0)) {
            throw new EmptyQuantityItems();
        }
    }

    public void validatedItemOrException(ProductPersistencePort productPersistencePort) {
        this.getItems().forEach(item -> item.setProduct(productPersistencePort.findByIdAndIdStore(item.getIdProduct(), this.getIdStore())
                .orElseThrow(() -> new ProductInOrderNotFound("Product with id " + item.getIdProduct() + ", by idStore: " + idStore + " not found"))));
    }

    public void calculateTotal(ProductPersistencePort productPersistencePort) {
        this.setTotal(this.getItems().stream()
                .map(item -> productPersistencePort.findById(item.getIdProduct())
                        .orElseThrow(ProductNotFound::new)
                        .getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow(ProductNotFound::new));
    }

    public void validatedPayments() {
        if (this.getPayment() != null && this.getPayment().getStatus().equals(PaymentStatus.APPROVED))
            throw new OrderPaymentApproved();
    }
}
