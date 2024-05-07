package br.com.techChallenge.adapters.inbound.controllers.order;

import br.com.techChallenge.adapters.dtos.order.request.OrderRequest;
import br.com.techChallenge.adapters.dtos.order.response.OrderResponse;
import br.com.techChallenge.core.domain.order.OrderDomain;
import br.com.techChallenge.core.domain.order.enums.StatusOrder;
import br.com.techChallenge.core.domain.order.item.OrderItemDomain;
import br.com.techChallenge.core.ports.order.OrderServicePort;
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

    final OrderServicePort orderServicePort;
    final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable UUID id) {
        OrderDomain orderDomain = orderServicePort.findById(id);
        OrderResponse orderResponse = modelMapper.map(orderDomain, OrderResponse.class);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<OrderResponse>> getByCpf(@PathVariable String cpf) {
        List<OrderDomain> orderDomains = orderServicePort.findByCpf(cpf);
        List<OrderResponse> orderResponses = orderDomains.stream()
                .map(orderDomain -> modelMapper.map(orderDomain, OrderResponse.class))
                .collect(Collectors.toList());

        if (orderResponses.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderDomain> orderDomains = orderServicePort.findAll();
        List<OrderResponse> orderResponses = orderDomains.stream()
                .map(orderDomain -> modelMapper.map(orderDomain, OrderResponse.class))
                .collect(Collectors.toList());

        if (orderResponses.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(orderResponses);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest orderRequest) {
        OrderDomain orderDomain = convertToOrderDomain(orderRequest);

        OrderDomain orderDomainSaved = orderServicePort.save(orderDomain, orderRequest.getPerson() != null ? orderRequest.getPerson().getCpf() : null);
        OrderResponse orderResponseSaved = modelMapper.map(orderDomainSaved, OrderResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseSaved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable UUID id, @RequestBody OrderRequest orderRequest) {
        OrderDomain orderDomain = convertToOrderDomain(orderRequest);

        OrderDomain orderDomainUpdated = orderServicePort.update(id, orderRequest.getPerson() != null ? orderRequest.getPerson().getCpf() : null, orderDomain.getItems());
        OrderResponse orderResponseUpdated = modelMapper.map(orderDomainUpdated, OrderResponse.class);
        return ResponseEntity.ok(orderResponseUpdated);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam StatusOrder status) {
        orderServicePort.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    private OrderDomain convertToOrderDomain(@RequestBody OrderRequest orderRequest) {
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setItems(orderRequest.getItems().stream()
                .map(item -> {
                    OrderItemDomain orderItemDomain = new OrderItemDomain();
                    orderItemDomain.setIdProduct(item.getIdProduct());
                    orderItemDomain.setQuantity(item.getQuantity());
                    return orderItemDomain;
                })
                .collect(Collectors.toList()));
        return orderDomain;
    }
}