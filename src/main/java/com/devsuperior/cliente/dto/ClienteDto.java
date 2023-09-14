package com.devsuperior.cliente.dto;

import com.devsuperior.cliente.entities.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDto {

    private Long id;

    @NotBlank(message = "Campo requerido")
    private String name;

    private String cpf;

    private Double income;

    @PastOrPresent(message = "Data de nascimento não pode ser data futura")
    private LocalDate birthDate;

    private Integer children;

    public ClienteDto(Cliente cliente) {
        new ModelMapper().map( cliente, this);
    }
}
