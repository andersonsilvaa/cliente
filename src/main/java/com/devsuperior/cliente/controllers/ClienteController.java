package com.devsuperior.cliente.controllers;

import com.devsuperior.cliente.dto.ClienteDto;
import com.devsuperior.cliente.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {

        ClienteDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDto>> findAll(Pageable pagina) {

        Page<ClienteDto> dto = service.findAll(pagina);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> save(@Valid @RequestBody ClienteDto dto) {

        dto = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Long id, @Valid @RequestBody ClienteDto dto) {

        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
