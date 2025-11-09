// package: com.lumen.cp56.domian.repository
package com.lumen.cp56.domian.repository;

import com.lumen.cp56.domian.model.MovimentacaoEstoque;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MovimentacaoEstoqueRepository {

    // Nosso "banco de dados falso" em memória
    private final Map<UUID, MovimentacaoEstoque> database = new ConcurrentHashMap<>();

    /**
     * Salva (cria ou atualiza) uma movimentação.
     * Gera um novo UUID se o ID for nulo.
     */
    public MovimentacaoEstoque save(MovimentacaoEstoque movimentacao) {
        if (movimentacao.getId() == null) {
            movimentacao.setId(UUID.randomUUID());
        }
        database.put(movimentacao.getId(), movimentacao);
        return movimentacao;
    }

    /**
     * Busca uma movimentação pelo seu ID.
     */
    public Optional<MovimentacaoEstoque> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    /**
     * Retorna todas as movimentações cadastradas.
     */
    public List<MovimentacaoEstoque> findAll() {
        return new ArrayList<>(database.values());
    }

    /**
     * Deleta uma movimentação (usado pelo Service).
     */
    public void delete(MovimentacaoEstoque movimentacao) {
        if (movimentacao != null) {
            database.remove(movimentacao.getId());
        }
    }

    /**
     * Método alternativo para deletar por ID.
     */
    public void deleteById(UUID id) {
        database.remove(id);
    }

    /**
     * Implementação FAKE do método customizado do JPA.
     * Busca por ID do reagente e ordena pela data (mais recente primeiro).
     */
    public List<MovimentacaoEstoque> findByReagenteIdOrderByDataHoraMovimentacaoDesc(UUID reagenteId) {
        return database.values().stream()
                .filter(mov -> mov.getReagente() != null && mov.getReagente().getId().equals(reagenteId))
                .sorted(Comparator.comparing(MovimentacaoEstoque::getDataHoraMovimentacao).reversed())
                .collect(Collectors.toList());
    }
}