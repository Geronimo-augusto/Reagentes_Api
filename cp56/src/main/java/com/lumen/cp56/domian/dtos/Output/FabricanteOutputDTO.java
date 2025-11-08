package com.lumen.cp56.domian.dtos.Output;

import java.util.UUID;

/**
 * DTO de SAÍDA para exibir dados de um Fabricante.
 */
public record FabricanteOutputDTO(
        UUID id,
        String nomeOficial,
        String nomeFantasia,
        String cnpj,
        String paisOrigem
) {
}