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

    @Autowired
    private FabricanteService fabricanteService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<FabricanteOutputDTO> listar(){
        return fabricanteService.findAll();
    }

    @GetMapping("/{fabricanteId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<FabricanteOutputDTO> buscar(@PathVariable UUID fabricanteId) {
        return ResponseEntity.ok(fabricanteService.findById(fabricanteId));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<FabricanteOutputDTO> adicionar(@RequestBody FabricanteInputDTO input) {
        FabricanteOutputDTO novoReagente =  fabricanteService.create(input);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoReagente.id())
                .toUri()
                ;

        return ResponseEntity.created(uri).body(novoReagente);
    }

    @PutMapping("/{fabricanteId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<FabricanteOutputDTO> atualizar(@PathVariable UUID fabricanteId, @RequestBody FabricanteInputDTO input) {
        return ResponseEntity.ok(fabricanteService.update(fabricanteId,input));
    }

    @DeleteMapping("/{fabricanteId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID fabricanteId) {
        fabricanteService.delete(fabricanteId);
    }
}