// package: com.lumen.cp56.domian.repository
package com.lumen.cp56.domian.repository;

import com.lumen.cp56.domian.model.Reagente;
import com.lumen.cp56.domian.model.StatusReagente;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository // O Spring ainda trata isso como um Bean!
public class ReagenteRepository {

    // Nosso "banco de dados falso" em memória
    // A chave é o UUID, o valor é o Reagente
    private final Map<UUID, Reagente> database = new ConcurrentHashMap<>();

    // Implementação FAKE do método save()
    public Reagente save(Reagente reagente) {
        if (reagente.getId() == null) {
            reagente.setId(UUID.randomUUID());
        }
        database.put(reagente.getId(), reagente);
        return reagente;
    }

    // Implementação FAKE do método findById()
    public Optional<Reagente> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    // Implementação FAKE do método findAll()
    public List<Reagente> findAll() {
        return new ArrayList<>(database.values());
    }

    // Implementação FAKE do método delete()
    public void delete(Reagente reagente) {
        database.remove(reagente.getId());
    }

    // Método que o Service usa
    public void deleteById(UUID id) {
        database.remove(id);
    }

    // --- Métodos customizados que você tinha antes ---

    public List<Reagente> findByStatus(StatusReagente status) {
        return database.values().stream()
                .filter(r -> r.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Reagente> findByDataValidadeBefore(LocalDate data) {
        return database.values().stream()
                .filter(r -> r.getDataValidade() != null && r.getDataValidade().isBefore(data))
                .collect(Collectors.toList());
    }

    public List<Reagente> findByFabricanteId(UUID fabricanteId) {
        return database.values().stream()
                .filter(r -> r.getFabricante() != null && r.getFabricante().getId().equals(fabricanteId))
                .collect(Collectors.toList());
    }
}