package com.lumen.cp56.controler;

import com.lumen.cp56.domian.dtos.Input.ReagenteInputDTO;
import com.lumen.cp56.domian.dtos.Output.ReagenteOutputDTO;
import com.lumen.cp56.domian.service.ReagenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reagentes") // rota mais descritiva
public class ReagenteController {

    @Autowired
    private ReagenteService reagenteService;

    @GetMapping
    public ResponseEntity<List<ReagenteOutputDTO>> listarTodos() {
        return ResponseEntity.ok(reagenteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReagenteOutputDTO> buscarPorId(@PathVariable("id") UUID idReagente) {
        return ResponseEntity.ok(reagenteService.findById(idReagente));
    }

    @PostMapping
    public ResponseEntity<ReagenteOutputDTO> criar(@RequestBody ReagenteInputDTO dadosEntrada) {
        ReagenteOutputDTO reagenteCriado = reagenteService.create(dadosEntrada);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reagenteCriado.id())
                .toUri();

        return ResponseEntity.created(uri).body(reagenteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReagenteOutputDTO> atualizar(
            @PathVariable("id") UUID idReagente,
            @RequestBody ReagenteInputDTO dadosEntrada) {
        return ResponseEntity.ok(reagenteService.update(idReagente, dadosEntrada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") UUID idReagente) {
        reagenteService.delete(idReagente);
        return ResponseEntity.noContent().build();
    }
}
