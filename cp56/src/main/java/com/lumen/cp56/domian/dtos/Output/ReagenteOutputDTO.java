package com.lumen.cp56.domian.dtos.Output;

import com.lumen.cp56.domian.model.StatusReagente;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO de SAÍDA para exibir dados de um Reagente.
 * Note que para os relacionamentos, usamos os DTOs de saída
 * correspondentes para um JSON aninhado e completo.
 */
public record ReagenteOutputDTO(
        UUID id,
        String nome,
        String codigoSku,
        String lote,
        LocalDate dataValidade,
        LocalDate dataRecebimento,
        Integer quantidadeEstoque,
        StatusReagente status,
        boolean estaVencido, // Campo calculado
        FabricanteOutputDTO fabricante,
        LocalizacaoEstoqueOutputDTO localizacaoEstoque
) {
}
