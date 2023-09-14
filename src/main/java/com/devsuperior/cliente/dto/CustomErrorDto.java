package com.devsuperior.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class CustomErrorDto {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}

