package com.lumen.cp56.domian.dtos.Input;

import com.lumen.cp56.domian.model.TipoMovimentacao;
import java.time.LocalDate;

/**
 * DTO de ENTRADA para criar uma MovimentacaoEstoque.
 * O ID do reagente virá da URL (ex: /reagentes/{id}/movimentacoes)
 */
public record MovimentacaoEstoqueInputDTO(
        String observacao,
        Integer quantidadeMovimentada,
        LocalDate dataBalanco, // dataHoraMovimentacao será gerada no servidor
        TipoMovimentacao tipo
) {
}