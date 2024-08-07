package br.com.techChallenge.adapters.inbound.controllers.product;

import br.com.techChallenge.adapters.dtos.product.ProductDTO;
import br.com.techChallenge.adapters.dtos.product.ProductInputDTO;
import br.com.techChallenge.core.domain.product.ProductDomain;
import br.com.techChallenge.core.ports.product.ProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ProductController {

    final ProductServicePort productServicePort;
    final ModelMapper modelMapper;

    @Operation(summary = "Get a product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable UUID id) {
        ProductDomain productDomain = productServicePort.findById(id);
        ProductDTO productDto = modelMapper.map(productDomain, ProductDTO.class);
        return ResponseEntity.ok(productDto);
    }

    @Operation(summary = "Get products by category ID")
    @GetMapping("/category/{idCategory}")
    public ResponseEntity<List<ProductDTO>> getByIdCategory(@PathVariable UUID idCategory) {
        List<ProductDomain> allByCategory = productServicePort.findAllByCategory(idCategory);

        List<ProductDTO> allProductDTOS = allByCategory.stream()
                .map(productDomain -> modelMapper.map(productDomain, ProductDTO.class))
                .collect(Collectors.toList());

        if (allProductDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(allProductDTOS);
    }

    @Operation(summary = "Get all products")
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDomain> allProducts = productServicePort.findAll();

        List<ProductDTO> allProductDTOS = allProducts.stream()
                .map(productDomain -> modelMapper.map(productDomain, ProductDTO.class))
                .collect(Collectors.toList());

        if (allProductDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(allProductDTOS);
    }

    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductInputDTO productInputDTO) {
        ProductDomain domain = modelMapper.map(productInputDTO, ProductDomain.class);
        ProductDomain savedPessoa = productServicePort.save(domain);
        ProductDTO dto = modelMapper.map(savedPessoa, ProductDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Update a product by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @RequestBody ProductInputDTO productInputDTO) {
        ProductDomain domain = modelMapper.map(productInputDTO, ProductDomain.class);
        domain.setId(id);

        ProductDomain updatedPessoa = productServicePort.update(domain);
        ProductDTO dto = modelMapper.map(updatedPessoa, ProductDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Delete a product by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productServicePort.deleteByID(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}