// package: com.lumen.cp56.domian.repository
package com.lumen.cp56.domian.repository;

import com.lumen.cp56.domian.model.LocalizacaoEstoque;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LocalizacaoEstoqueRepository {

    // Nosso "banco de dados falso" em memória
    private final Map<UUID, LocalizacaoEstoque> database = new ConcurrentHashMap<>();

    /**
     * Salva (cria ou atualiza) uma localização.
     * Gera um novo UUID se o ID for nulo.
     */
    public LocalizacaoEstoque save(LocalizacaoEstoque localizacao) {
        if (localizacao.getId() == null) {
            localizacao.setId(UUID.randomUUID());
        }
        database.put(localizacao.getId(), localizacao);
        return localizacao;
    }

    /**
     * Busca uma localização pelo seu ID.
     */
    public Optional<LocalizacaoEstoque> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    /**
     * Retorna todas as localizações cadastradas.
     */
    public List<LocalizacaoEstoque> findAll() {
        return new ArrayList<>(database.values());
    }

    /**
     * Deleta uma localização (usado pelo Service).
     */
    public void delete(LocalizacaoEstoque localizacao) {
        if (localizacao != null) {
            database.remove(localizacao.getId());
        }
    }

    /**
     * Método alternativo para deletar por ID.
     */
    public void deleteById(UUID id) {
        database.remove(id);
    }
}