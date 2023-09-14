package com.devsuperior.cliente.services;

import com.devsuperior.cliente.dto.ClienteDto;
import com.devsuperior.cliente.entities.Cliente;
import com.devsuperior.cliente.repositories.ClienteRepository;
import com.devsuperior.cliente.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public ClienteDto findById(Long id) {

        Cliente cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente inexistente"));
        return new ModelMapper().map(cliente, ClienteDto.class);
    }

    @Transactional(readOnly = true)
    public Page<ClienteDto> findAll(Pageable pagina) {

        Page<Cliente> clientes = repository.findAll(pagina);
        return clientes.map(cliente -> new ClienteDto(cliente));
    }

    @Transactional
    public ClienteDto save(ClienteDto dto) {

        Cliente produto = new Cliente(dto);
        produto = repository.save(produto);
        return new ClienteDto(produto);
    }

    @Transactional
    public ClienteDto update(Long id, ClienteDto dto) {

        try {
            Cliente cliente = repository.getReferenceById(id);
            cliente.dtoToEntity(dto);
            cliente = repository.save(cliente);
            return new ClienteDto(cliente);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente inexistente");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente inexistente");
        }
        repository.deleteById(id);
    }
}
