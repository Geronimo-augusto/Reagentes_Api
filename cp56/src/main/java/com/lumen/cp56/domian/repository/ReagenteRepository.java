// package: com.lumen.cp56.domian.repository
package com.lumen.cp56.domian.repository;

import com.lumen.cp56.domian.model.Reagente;
import com.lumen.cp56.domian.model.StatusReagente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReagenteRepository extends JpaRepository<Reagente, UUID> {

    List<Reagente> findByStatus(StatusReagente status);

    List<Reagente> findByDataValidadeBefore(LocalDate data);

    List<Reagente> findByFabricanteId(UUID fabricanteId);
}