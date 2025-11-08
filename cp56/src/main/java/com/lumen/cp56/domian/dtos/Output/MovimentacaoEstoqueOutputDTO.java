package com.lumen.cp56.domian.dtos.Output;

import com.lumen.cp56.domian.model.TipoMovimentacao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO de SAÍDA para exibir dados de uma MovimentacaoEstoque.
 */
public record MovimentacaoEstoqueOutputDTO(
        UUID id,
        String observacao,
        Integer quantidadeMovimentada,
        LocalDateTime dataHoraMovimentacao,
        LocalDate dataBalanco,
        TipoMovimentacao tipo,
        UUID reagenteId // Apenas o ID do reagente para evitar circularidade
) {
}