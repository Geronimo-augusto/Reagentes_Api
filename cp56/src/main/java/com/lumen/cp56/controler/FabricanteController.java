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

/**
 * Controlador REST responsável por gerenciar os endpoints relacionados à entidade Fabricante.
 * Este controller expõe as operações CRUD (Create, Read, Update e Delete) por meio de uma API REST.
 */
@RestController
@RequestMapping("/fabricantes") // / Define a URL base para todos os endpoints deste controlador
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService; // Injeta o serviço responsável pela regra de negócio de Fabricante

    /**
     * Endpoint para listar todos os fabricantes cadastrados.
     * Método: GET /fabricantes
     * @return Lista de FabricanteOutputDTO (dados de saída formatados)
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<FabricanteOutputDTO> listar(){
        return fabricanteService.findAll();
    }

    /**
     * Endpoint para buscar um fabricante específico pelo seu ID.
     * Método: GET /fabricantes/{fabricanteId}
     * @param fabricanteId UUID do fabricante a ser buscado
     * @return Objeto FabricanteOutputDTO com os dados do fabricante encontrado
     */
    @GetMapping("/{fabricanteId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<FabricanteOutputDTO> buscar(@PathVariable UUID fabricanteId) {
        return ResponseEntity.ok(fabricanteService.findById(fabricanteId));
    }

    /**
     * Endpoint para adicionar um novo fabricante.
     * Método: POST /fabricantes
     * @param input Dados de entrada do novo fabricante (DTO)
     * @return ResponseEntity contendo o objeto criado e a URI de acesso ao recurso
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<FabricanteOutputDTO> adicionar(@RequestBody FabricanteInputDTO input) {
        
        // Cria o novo fabricante por meio do serviço
        FabricanteOutputDTO novoReagente =  fabricanteService.create(input);
        
        // Gera a URI do novo recurso criado (ex: /fabricantes/{id})
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoReagente.id())
                .toUri()
                ;

        // Retorna resposta HTTP 201 (Created) com o corpo e o cabeçalho "Location"        
        return ResponseEntity.created(uri).body(novoReagente);
    }

    /**
     * Endpoint para atualizar um fabricante existente.
     * Método: PUT /fabricantes/{fabricanteId}
     * @param fabricanteId UUID do fabricante que será atualizado
     * @param input Dados atualizados (DTO)
     * @return ResponseEntity contendo o fabricante atualizado
     */
    @PutMapping("/{fabricanteId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<FabricanteOutputDTO> atualizar(@PathVariable UUID fabricanteId, @RequestBody FabricanteInputDTO input) {
        return ResponseEntity.ok(fabricanteService.update(fabricanteId,input));
    }

    /**
     * Endpoint para excluir um fabricante do sistema.
     * Método: DELETE /fabricantes/{fabricanteId}
     * @param fabricanteId UUID do fabricante a ser excluído
     */
    @DeleteMapping("/{fabricanteId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID fabricanteId) {
        fabricanteService.delete(fabricanteId);
    }
}
