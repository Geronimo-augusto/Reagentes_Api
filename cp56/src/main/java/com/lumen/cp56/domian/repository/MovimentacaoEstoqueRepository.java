// package: com.lumen.cp56.domian.repository
package com.lumen.cp56.domian.repository;

import com.lumen.cp56.domian.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, UUID> {

    List<MovimentacaoEstoque> findByReagenteIdOrderByDataHoraMovimentacaoDesc(UUID reagenteId);
}