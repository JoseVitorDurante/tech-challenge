package br.com.techChallenge.adapters.dtos.order.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private List<OrderItemRequest> items = new ArrayList<>();

    private PersonRequest person;
}
