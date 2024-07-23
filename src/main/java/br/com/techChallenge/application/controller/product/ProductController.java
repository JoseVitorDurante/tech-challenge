package br.com.techChallenge.application.controller.product;

import br.com.techChallenge.application.dtos.product.ProductDTO;
import br.com.techChallenge.application.dtos.product.ProductInputDTO;
import br.com.techChallenge.domain.entity.product.ProductDomain;
import br.com.techChallenge.domain.useCases.product.*;
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

    private final FindProductById findProductById;
    private final FindAllByCategoryId findAllByCategoryId;
    private final FindAllProducts findAllProducts;
    private final CreateNewProduct createNewProduct;
    private final UpdateProduct updateProduct;
    private final DeleteProductById deleteProductById;
    private final ModelMapper modelMapper;

    @Operation(summary = "Get a product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable UUID id) {
        ProductDomain productDomain = findProductById.execute(id);
        ProductDTO productDto = modelMapper.map(productDomain, ProductDTO.class);
        return ResponseEntity.ok(productDto);
    }

    @Operation(summary = "Get products by category ID")
    @GetMapping("/category/{idCategory}")
    public ResponseEntity<List<ProductDTO>> getByIdCategory(@PathVariable UUID idCategory) {
        List<ProductDomain> allByCategory = findAllByCategoryId.execute(idCategory);

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
        List<ProductDomain> allProducts = findAllProducts.execute();

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
        ProductDomain savedPessoa = createNewProduct.execute(domain);
        ProductDTO dto = modelMapper.map(savedPessoa, ProductDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Update a product by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @RequestBody ProductInputDTO productInputDTO) {
        ProductDomain domain = modelMapper.map(productInputDTO, ProductDomain.class);
        domain.setId(id);

        ProductDomain updatedPessoa = updateProduct.execute(domain);
        ProductDTO dto = modelMapper.map(updatedPessoa, ProductDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Delete a product by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteProductById.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}