package br.com.techChallenge.adapters.inbound.controllers.category;

import br.com.techChallenge.adapters.dtos.category.CategoryDTO;
import br.com.techChallenge.adapters.dtos.category.CategoryInputDTO;
import br.com.techChallenge.core.domain.category.CategoryDomain;
import br.com.techChallenge.core.ports.category.CategoryServicePort;
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

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable UUID id) {
        CategoryDomain CategoryDomain = categoryServicePort.findById(id);
        CategoryDTO categoryDto = modelMapper.map(CategoryDomain, CategoryDTO.class);
        return ResponseEntity.ok(categoryDto);
    }

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

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryInputDTO categoryInputDTO) {
        CategoryDomain domain = modelMapper.map(categoryInputDTO, CategoryDomain.class);
        CategoryDomain savedPessoa = categoryServicePort.save(domain);
        CategoryDTO dto = modelMapper.map(savedPessoa, CategoryDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable UUID id, @RequestBody CategoryInputDTO categoryInputDTO) {
        CategoryDomain domain = modelMapper.map(categoryInputDTO, CategoryDomain.class);
        domain.setId(id);

        CategoryDomain updatedPessoa = categoryServicePort.update(domain);
        CategoryDTO dto = modelMapper.map(updatedPessoa, CategoryDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        CategoryDomain domain = new CategoryDomain();
        domain.setId(id);
        categoryServicePort.delete(domain);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}