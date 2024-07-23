package br.com.techChallenge.application.controller.store;

import br.com.techChallenge.application.dtos.store.StoreDTO;
import br.com.techChallenge.application.dtos.store.StoreInputDTO;
import br.com.techChallenge.domain.entity.store.StoreDomain;
import br.com.techChallenge.domain.useCases.store.CreateNewStore;
import br.com.techChallenge.domain.useCases.store.DeleteStoreById;
import br.com.techChallenge.domain.useCases.store.FindAllStores;
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
@RequestMapping("/store")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class StoreController {

    private final CreateNewStore createNewStore;
    private final FindAllStores findAllStores;
    private final DeleteStoreById deleteStoreById;
    private final ModelMapper modelMapper;

    @Operation(summary = "Create a new store")
    @PostMapping
    public ResponseEntity<StoreDTO> save(@RequestBody @Valid StoreInputDTO storeInputDTO) {
        StoreDomain domain = modelMapper.map(storeInputDTO, StoreDomain.class);
        StoreDomain savedPessoa = createNewStore.execute(domain);
        StoreDTO dto = modelMapper.map(savedPessoa, StoreDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Get all stores")
    @GetMapping("/all")
    public ResponseEntity<List<StoreDTO>> getAll() {
        List<StoreDomain> allStores = findAllStores.execute();

        List<StoreDTO> storeDTOList = allStores.stream()
                .map(domain -> modelMapper.map(domain, StoreDTO.class))
                .collect(Collectors.toList());

        if (storeDTOList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(storeDTOList);
    }

    @Operation(summary = "Delete a store by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteStoreById.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}