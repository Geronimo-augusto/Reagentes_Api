package com.lumen.cp56.controler;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lumen.cp56.domian.dtos.Input.MovimentacaoEstoqueInputDTO;
import com.lumen.cp56.domian.dtos.Output.MovimentacaoEstoqueOutputDTO;
import com.lumen.cp56.domian.service.MovimentacaoEstoqueService;


@RestController
@RequestMapping("/reagentes/{reagenteId}/movimentacoes") 
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService movimentacaoService;


    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoqueOutputDTO>> getAllByReagente(
            @PathVariable UUID reagenteId) {

        return ResponseEntity.ok(movimentacaoService.findAllByReagenteId(reagenteId));
    }

    // Cria uma nova movimentação PARA ESTE reagente
    @PostMapping
    public ResponseEntity<MovimentacaoEstoqueOutputDTO> create(
            @PathVariable UUID reagenteId,
            @RequestBody MovimentacaoEstoqueInputDTO dto) {

        MovimentacaoEstoqueOutputDTO novaMovimentacao = movimentacaoService.create(reagenteId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMovimentacao);
    }

    // Deleta uma movimentação específica
    @DeleteMapping("/{movimentacaoId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID reagenteId, // Não usado pelo service, mas faz parte da URL
            @PathVariable UUID movimentacaoId) {

        movimentacaoService.delete(movimentacaoId);
        return ResponseEntity.noContent().build();
    }
}