// package: com.lumen.cp56.domian.repository
package com.lumen.cp56.domian.repository;

import com.lumen.cp56.domian.model.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, UUID> {
}