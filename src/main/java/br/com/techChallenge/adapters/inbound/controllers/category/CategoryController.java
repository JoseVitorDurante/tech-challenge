package br.com.techChallenge.adapters.inbound.controllers.category;

import br.com.techChallenge.adapters.dtos.category.CategoryDTO;
import br.com.techChallenge.adapters.dtos.category.CategoryInputDTO;
import br.com.techChallenge.core.domain.category.CategoryDomain;
import br.com.techChallenge.core.ports.category.CategoryServicePort;
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
@RequestMapping("/category")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CategoryController {

    final CategoryServicePort categoryServicePort;
    final ModelMapper modelMapper;

    @Operation(summary = "Get a category by id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable UUID id) {
        CategoryDomain categoryDomain = categoryServicePort.findById(id);
        CategoryDTO categoryDto = modelMapper.map(categoryDomain, CategoryDTO.class);
        return ResponseEntity.ok(categoryDto);
    }

    @Operation(summary = "Get all categories")
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDomain> allCategories = categoryServicePort.findAll();

        List<CategoryDTO> allCategoryDTOS = allCategories.stream()
                .map(categoryDomain -> modelMapper.map(categoryDomain, CategoryDTO.class))
                .collect(Collectors.toList());

        if (allCategoryDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(allCategoryDTOS);
    }

    @Operation(summary = "Create a new category")
    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody @Valid CategoryInputDTO categoryInputDTO) {
        CategoryDomain domain = modelMapper.map(categoryInputDTO, CategoryDomain.class);
        CategoryDomain categoryDomain = categoryServicePort.save(domain);
        CategoryDTO dto = modelMapper.map(categoryDomain, CategoryDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Update a category by id")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable UUID id, @RequestBody CategoryInputDTO categoryInputDTO) {
        CategoryDomain domain = modelMapper.map(categoryInputDTO, CategoryDomain.class);
        domain.setId(id);

        CategoryDomain updatedPessoa = categoryServicePort.update(domain);
        CategoryDTO dto = modelMapper.map(updatedPessoa, CategoryDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Delete a category by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryServicePort.deleteByID(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}