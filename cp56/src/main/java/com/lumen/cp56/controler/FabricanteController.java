package com.lumen.cp56.controler;

import com.lumen.cp56.domian.dtos.Input.FabricanteInputDTO;
import com.lumen.cp56.domian.dtos.Output.FabricanteOutputDTO;
import com.lumen.cp56.domian.service.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fabricantes") // URL Base
public class FabricanteController {

    private final FabricanteService fabricanteService;

    @Autowired
    public FabricanteController(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FabricanteOutputDTO> listar() {
        return fabricanteService.findAll();
    }

    @GetMapping("/{fabricanteId}")
    public ResponseEntity<FabricanteOutputDTO> buscar(@PathVariable UUID fabricanteId) {
        FabricanteOutputDTO fabricante = fabricanteService.findById(fabricanteId);
        if (fabricante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fabricante);
    }

    @PostMapping
    public ResponseEntity<FabricanteOutputDTO> adicionar(@RequestBody FabricanteInputDTO input) {
        FabricanteOutputDTO novoFabricante = fabricanteService.create(input);

        if (novoFabricante == null || novoFabricante.id() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoFabricante.id())
                .toUri();

        return ResponseEntity.created(uri).body(novoFabricante);
    }

    @PutMapping("/{fabricanteId}")
    public ResponseEntity<FabricanteOutputDTO> atualizar(
            @PathVariable UUID fabricanteId,
            @RequestBody FabricanteInputDTO input) {

        FabricanteOutputDTO atualizado = fabricanteService.update(fabricanteId, input);

        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{fabricanteId}")
    public ResponseEntity<Void> excluir(@PathVariable UUID fabricanteId) {
        try {
            fabricanteService.delete(fabricanteId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
