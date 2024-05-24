package br.com.techChallenge.adapters.inbound.controllers.customer;

import br.com.techChallenge.adapters.dtos.customer.CustomerDTO;
import br.com.techChallenge.adapters.dtos.customer.CustomerInputDTO;
import br.com.techChallenge.core.domain.customer.CustomerDomain;
import br.com.techChallenge.core.ports.customer.CustomerServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CustomerController {

    final CustomerServicePort customerServicePort;
    final ModelMapper modelMapper;

    @Operation(summary = "Get a customer by id")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable UUID id) {
        CustomerDomain customerDomainOptional = customerServicePort.findById(id);
        CustomerDTO customerDTO = modelMapper.map(customerDomainOptional, CustomerDTO.class);
        return ResponseEntity.ok(customerDTO);

    }

    @Operation(summary = "Get all customers")
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDomain> allCustomers = customerServicePort.findAll();

        List<CustomerDTO> allCustomerDTOS = allCustomers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());

        if (allCustomerDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(allCustomerDTOS);
    }

    @Operation(summary = "Get a customer by CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<CustomerDTO> findCustomerByCpf(@PathVariable String cpf) {
        Optional<CustomerDomain> customerDomainOptional = customerServicePort.findByCpf(cpf);
        if (customerDomainOptional.isPresent()) {
            CustomerDTO customerDTO = modelMapper.map(customerDomainOptional.get(), CustomerDTO.class);
            return ResponseEntity.ok(customerDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create a new customer")
    @PostMapping
    public ResponseEntity<CustomerDTO> saveNewCustomer(@RequestBody @Valid CustomerInputDTO customerInputDTO) {
        CustomerDomain domain = modelMapper.map(customerInputDTO, CustomerDomain.class);
        CustomerDomain savedPessoa = customerServicePort.save(domain);
        CustomerDTO dto = modelMapper.map(savedPessoa, CustomerDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Update a customer by id")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable UUID id, @RequestBody CustomerInputDTO customerInputDTO) {
        CustomerDomain domain = modelMapper.map(customerInputDTO, CustomerDomain.class);
        domain.setId(id);

        CustomerDomain updatedPessoa = customerServicePort.update(domain);
        CustomerDTO dto = modelMapper.map(updatedPessoa, CustomerDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Delete a customer by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable UUID id) {
        CustomerDomain domain = new CustomerDomain();
        domain.setId(id);
        customerServicePort.delete(domain);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}