package com.lumen.cp56.controler;

import com.lumen.cp56.domian.dtos.Input.ReagenteInputDTO;
import com.lumen.cp56.domian.dtos.Output.ReagenteOutputDTO;
import com.lumen.cp56.domian.service.ReagenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reagentes")
public class ReagenteController {

    @Autowired
    private ReagenteService reagenteService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<ReagenteOutputDTO> lista = reagenteService.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao listar reagentes: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") UUID idReagente) {
        try {
            ReagenteOutputDTO reagente = reagenteService.findById(idReagente);
            return ResponseEntity.ok(reagente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reagente não encontrado: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ReagenteInputDTO dadosEntrada) {
        try {
            ReagenteOutputDTO reagenteCriado = reagenteService.create(dadosEntrada);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(reagenteCriado.id())
                    .toUri();

            return ResponseEntity.created(uri).body(reagenteCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Dados inválidos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar reagente: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") UUID idReagente,
                                       @RequestBody ReagenteInputDTO dadosEntrada) {
        try {
            ReagenteOutputDTO atualizado = reagenteService.update(idReagente, dadosEntrada);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reagente não encontrado: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") UUID idReagente) {
        try {
            reagenteService.delete(idReagente);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reagente não encontrado: " + e.getMessage());
        }
    }
}
