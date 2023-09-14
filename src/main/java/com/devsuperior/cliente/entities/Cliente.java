package com.devsuperior.cliente.entities;

import com.devsuperior.cliente.dto.ClienteDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "income")
    private Double income;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "children")
    private Integer children;

    public Cliente(ClienteDto dto) {
        new ModelMapper().map(dto, this);
    }

    public void dtoToEntity(ClienteDto dto) {
        new ModelMapper().typeMap(ClienteDto.class, Cliente.class)
                .addMappings(mapper -> mapper.skip(Cliente::setId))
                .map(dto, this);
    }
}
