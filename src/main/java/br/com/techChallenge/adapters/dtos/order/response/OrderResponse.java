package br.com.techChallenge.adapters.dtos.order.response;

import br.com.techChallenge.adapters.dtos.order.request.OrderItemRequest;
import br.com.techChallenge.core.domain.order.enums.StatusOrder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    @NotNull
    private UUID id;

    @NotEmpty
    private List<OrderItemRequest> items = new ArrayList<>();

    private StatusOrder status;

    private BigDecimal total;

    private String idPerson;

    private String cpf;
}
