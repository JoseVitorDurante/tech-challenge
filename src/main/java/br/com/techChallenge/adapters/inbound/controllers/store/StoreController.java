package br.com.techChallenge.adapters.inbound.controllers.store;

import br.com.techChallenge.adapters.dtos.store.StoreDTO;
import br.com.techChallenge.adapters.dtos.store.StoreInputDTO;
import br.com.techChallenge.core.domain.store.StoreDomain;
import br.com.techChallenge.core.ports.store.StoreServicePort;
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

    final StoreServicePort storeServicePort;
    final ModelMapper modelMapper;

    @Operation(summary = "Create a new store")
    @PostMapping
    public ResponseEntity<StoreDTO> save(@RequestBody @Valid StoreInputDTO storeInputDTO) {
        StoreDomain domain = modelMapper.map(storeInputDTO, StoreDomain.class);
        StoreDomain savedPessoa = storeServicePort.save(domain);
        StoreDTO dto = modelMapper.map(savedPessoa, StoreDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Get all stores")
    @GetMapping("/all")
    public ResponseEntity<List<StoreDTO>> getAll() {
        List<StoreDomain> allStores = storeServicePort.findAll();

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
        storeServicePort.deleteByID(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}