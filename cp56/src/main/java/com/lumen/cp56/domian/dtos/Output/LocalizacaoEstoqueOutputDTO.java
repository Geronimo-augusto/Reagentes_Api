package com.lumen.cp56.domian.dtos.Output;

import com.lumen.cp56.domian.model.TipoLocalizacaoEstoque;
import java.util.UUID;

/**
 * DTO de SAÍDA para exibir dados de uma LocalizacaoEstoque.
 */
public record LocalizacaoEstoqueOutputDTO(
        UUID id,
        String codigoLocal,
        String descricao,
        String setor,
        String faixaTemperaturaNominal,
        TipoLocalizacaoEstoque tipo
) {
}