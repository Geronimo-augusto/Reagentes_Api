package com.lumen.cp56.domian.dtos.Input;

import com.lumen.cp56.domian.model.TipoLocalizacaoEstoque;

/**
 * DTO de ENTRADA para criar ou atualizar uma LocalizacaoEstoque.
 */
public record LocalizacaoEstoqueInputDTO(
        String codigoLocal,
        String descricao,
        String setor,
        String faixaTemperaturaNominal,
        TipoLocalizacaoEstoque tipo
) {
}