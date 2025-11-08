package com.lumen.cp56.domian.dtos.Input;

/**
 * DTO de ENTRADA para criar ou atualizar um Fabricante.
 */
public record FabricanteInputDTO(
        String nomeOficial,
        String nomeFantasia,
        String cnpj,
        String paisOrigem
) {}
