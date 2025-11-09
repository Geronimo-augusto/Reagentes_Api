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
@RequestMapping("/reagentes") // URL Base
public class ReagenteController {

    @Autowired
    private ReagenteService reagenteService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ReagenteOutputDTO> listar(){
       return reagenteService.findAll();
    }

    @GetMapping("/{reagenteId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ReagenteOutputDTO> buscar(@PathVariable UUID reagenteId) {
        return ResponseEntity.ok(reagenteService.findById(reagenteId));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ReagenteOutputDTO> adicionar(@RequestBody ReagenteInputDTO input) {
        ReagenteOutputDTO novoReagente =  reagenteService.create(input);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoReagente.id())
                .toUri()
                ;

        return ResponseEntity.created(uri).body(novoReagente);
    }

    @PutMapping("/{reagenteId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ReagenteOutputDTO> atualizar(@PathVariable UUID reagenteId, @RequestBody ReagenteInputDTO input) {
        return ResponseEntity.ok(reagenteService.update(reagenteId,input));
    }

    @DeleteMapping("/{reagenteId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID reagenteId) {
        reagenteService.delete(reagenteId);
    }

}