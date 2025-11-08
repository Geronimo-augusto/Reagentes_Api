package com.lumen.cp56.domian.dtos.Input;

import com.lumen.cp56.domian.model.StatusReagente;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO de ENTRADA para criar ou atualizar um Reagente.
 * Note que para os relacionamentos, pedimos apenas os IDs.
 */
public record ReagenteInputDTO(
        String nome,
        String codigoSku,
        String lote,
        LocalDate dataValidade,
        LocalDate dataRecebimento,
        Integer quantidadeEstoque,
        StatusReagente status,
        UUID fabricanteId,
        UUID localizacaoEstoqueId
) {
}
