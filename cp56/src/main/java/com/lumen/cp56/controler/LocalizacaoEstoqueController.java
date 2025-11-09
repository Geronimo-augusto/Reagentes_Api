package com.lumen.cp56.controler;

import com.lumen.cp56.domian.dtos.Input.LocalizacaoEstoqueInputDTO;
import com.lumen.cp56.domian.dtos.Output.LocalizacaoEstoqueOutputDTO;
import com.lumen.cp56.domian.service.LocalizacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/localizacoes-estoque") // URL Base
public class LocalizacaoEstoqueController {

    @Autowired
    private LocalizacaoEstoqueService localizacaoEstoqueService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<LocalizacaoEstoqueOutputDTO> listar(){
        return localizacaoEstoqueService.findAll();
    }


    @GetMapping("/{localizacaoId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<LocalizacaoEstoqueOutputDTO> buscar(@PathVariable UUID localizacaoId) {
        return ResponseEntity.ok(localizacaoEstoqueService.findById(localizacaoId));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<LocalizacaoEstoqueOutputDTO> adicionar(@RequestBody LocalizacaoEstoqueInputDTO input) {
        LocalizacaoEstoqueOutputDTO novoLocal =  localizacaoEstoqueService.create(input);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoLocal.id())
                .toUri()
                ;

        return ResponseEntity.created(uri).body(novoLocal);
    }


    @PutMapping("/{localizacaoId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<LocalizacaoEstoqueOutputDTO> atualizar(@PathVariable UUID localizacaoId, @RequestBody LocalizacaoEstoqueInputDTO input) {
        return ResponseEntity.ok(localizacaoEstoqueService.update(localizacaoId, input));
    }


    @DeleteMapping("/{localizacaoId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID localizacaoId) {
        localizacaoEstoqueService.delete(localizacaoId);
    }
}