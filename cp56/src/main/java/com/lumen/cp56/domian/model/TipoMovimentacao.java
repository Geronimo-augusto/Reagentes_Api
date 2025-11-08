package com.lumen.cp56.domian.model;

/**
 * Enum que representa os tipos de movimentação de estoque.
 */
public enum TipoMovimentacao {
    ENTRADA_NOTA,
    SAIDA_USO_ANALISADOR,
    SAIDA_DESCARTE_VENCIMENTO,
    SAIDA_DESCARTE_CONTROLE_QUALIDADE,
    AJUSTE_INVENTARIO_POSITIVO,
    AJUSTE_INVENTARIO_NEGATIVO
}