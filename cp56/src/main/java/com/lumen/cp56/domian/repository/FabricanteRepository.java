// package: com.lumen.cp56.domian.repository
package com.lumen.cp56.domian.repository;

import com.lumen.cp56.domian.model.Fabricante;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FabricanteRepository {

    // Nosso "banco de dados falso" em memória
    private final Map<UUID, Fabricante> database = new ConcurrentHashMap<>();

    /**
     * Salva (cria ou atualiza) um fabricante.
     * Gera um novo UUID se o ID for nulo.
     */
    public Fabricante save(Fabricante fabricante) {
        if (fabricante.getId() == null) {
            fabricante.setId(UUID.randomUUID());
        }
        database.put(fabricante.getId(), fabricante);
        return fabricante;
    }

    /**
     * Busca um fabricante pelo seu ID.
     */
    public Optional<Fabricante> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    /**
     * Retorna todos os fabricantes cadastrados.
     */
    public List<Fabricante> findAll() {
        return new ArrayList<>(database.values());
    }

    /**
     * Deleta um fabricante (usado pelo Service).
     */
    public void delete(Fabricante fabricante) {
        if (fabricante != null) {
            database.remove(fabricante.getId());
        }
    }

    /**
     * Método alternativo para deletar por ID.
     */
    public void deleteById(UUID id) {
        database.remove(id);
    }
}