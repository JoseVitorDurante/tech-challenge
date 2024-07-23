package br.com.techChallenge.application.controller.order;

import br.com.techChallenge.application.dtos.order.request.OrderRequest;
import br.com.techChallenge.application.dtos.order.response.OrderResponse;
import br.com.techChallenge.domain.entity.order.OrderDomain;
import br.com.techChallenge.domain.entity.order.enums.StatusOrder;
import br.com.techChallenge.domain.entity.order.item.OrderItemDomain;
import br.com.techChallenge.domain.useCases.order.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class OrderController {

    private final FindOrderById findOrderById;
    private final FindOrdersByCPF findOrdersByCPF;
    private final FindAllOrders findAllOrders;
    private final FindAllOrdersOrdered findAllOrdersOrdered;
    private final CreateNewOrder createNewOrder;
    private final UpdateOrder updateOrder;
    private final UpdateOrderStatus updateOrderStatus;
    private final ModelMapper modelMapper;

    @Operation(summary = "Get a order by id")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable UUID id) {
        OrderDomain orderDomain = findOrderById.execute(id);
        OrderResponse orderResponse = modelMapper.map(orderDomain, OrderResponse.class);
        return ResponseEntity.ok(orderResponse);
    }

    @Operation(summary = "Get orders by CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<OrderResponse>> getByCpf(@PathVariable String cpf) {
        List<OrderDomain> orderDomains = findOrdersByCPF.execute(cpf);
        List<OrderResponse> orderRespons = orderDomains.stream()
                .map(orderDomain -> modelMapper.map(orderDomain, OrderResponse.class))
                .collect(Collectors.toList());

        if (orderRespons.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(orderRespons);
    }

    @Operation(summary = "Get all orders")
    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderDomain> orderDomains = findAllOrders.execute();
        List<OrderResponse> orderRespons = orderDomains.stream()
                .map(orderDomain -> modelMapper.map(orderDomain, OrderResponse.class))
                .collect(Collectors.toList());

        if (orderRespons.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(orderRespons);
    }

    @Operation(summary = "Get all ordered by status and date")
    @GetMapping("/all/ordered")
    public ResponseEntity<List<OrderResponse>> getAllOrdered() {
        List<OrderDomain> orderDomains = findAllOrdersOrdered.execute();
        List<OrderResponse> orderRespons = orderDomains.stream()
                .map(orderDomain -> modelMapper.map(orderDomain, OrderResponse.class))
                .collect(Collectors.toList());

        if (orderRespons.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(orderRespons);
    }

    @Operation(summary = "Create a new order")
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest orderRequest) {
        OrderDomain orderDomain = convertToOrderDomain(orderRequest);

        OrderDomain orderDomainSaved = createNewOrder.execute(orderDomain, orderRequest.getCustomer() != null ? orderRequest.getCustomer().getCpf() : null, orderRequest.getPaymentType());
        OrderResponse orderResponseSaved = modelMapper.map(orderDomainSaved, OrderResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseSaved);
    }

    @Operation(summary = "Update a order by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable UUID id, @RequestBody OrderRequest orderRequest) {
        OrderDomain orderDomain = convertToOrderDomain(orderRequest);

        OrderDomain orderDomainUpdated = updateOrder.execute(id, orderRequest.getCustomer() != null ? orderRequest.getCustomer().getCpf() : null, orderDomain.getItems(), orderRequest.getPaymentType());
        OrderResponse orderResponseUpdated = modelMapper.map(orderDomainUpdated, OrderResponse.class);
        return ResponseEntity.ok(orderResponseUpdated);
    }

    @Operation(summary = "Update order status")
    @PutMapping("/status/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam StatusOrder status) {
        updateOrderStatus.execute(id, status);
        return ResponseEntity.ok().build();
    }

    private OrderDomain convertToOrderDomain(@RequestBody OrderRequest orderRequest) {
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setIdStore(orderRequest.getIdStore());
        orderDomain.setItems(orderRequest.getItems().stream()
                .map(item -> {
                    OrderItemDomain orderItemDomain = new OrderItemDomain();
                    orderItemDomain.setIdProduct(item.getIdProduct());
                    orderItemDomain.setQuantity(item.getQuantity());
                    orderItemDomain.setObservation(item.getObservation());
                    return orderItemDomain;
                })
                .collect(Collectors.toList()));
        return orderDomain;
    }
}