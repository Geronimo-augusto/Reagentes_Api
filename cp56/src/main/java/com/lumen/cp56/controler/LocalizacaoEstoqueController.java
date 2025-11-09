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

/**
 * Controlador REST responsável por gerenciar os endpoints relacionados à entidade Localização de Estoque.
 * Esta classe expõe as operações CRUD (Create, Read, Update e Delete) para manipulação das localizações
 * onde os insumos e reagentes são armazenados no sistema.
 */
@RestController
@RequestMapping("/localizacoes-estoque") // Define a URL base para todos os endpoints deste controlador
public class LocalizacaoEstoqueController {

    @Autowired
    private LocalizacaoEstoqueService localizacaoEstoqueService; // Serviço responsável pela lógica de negócio


    /**
     * Endpoint para listar todas as localizações de estoque cadastradas.
     * Método: GET /localizacoes-estoque
     * @return Lista de LocalizacaoEstoqueOutputDTO (dados de saída formatados)
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<LocalizacaoEstoqueOutputDTO> listar(){
        return localizacaoEstoqueService.findAll();
    }


    /**
     * Endpoint para buscar uma localização de estoque específica pelo seu ID.
     * Método: GET /localizacoes-estoque/{localizacaoId}
     * @param localizacaoId UUID da localização de estoque a ser buscada
     * @return ResponseEntity contendo o LocalizacaoEstoqueOutputDTO encontrado
     */
    @GetMapping("/{localizacaoId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<LocalizacaoEstoqueOutputDTO> buscar(@PathVariable UUID localizacaoId) {
        return ResponseEntity.ok(localizacaoEstoqueService.findById(localizacaoId));
    }

    /**
     * Endpoint para adicionar uma nova localização de estoque.
     * Método: POST /localizacoes-estoque
     * @param input Dados de entrada para criação da nova localização (DTO)
     * @return ResponseEntity com o recurso criado e URI de acesso ao mesmo
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<LocalizacaoEstoqueOutputDTO> adicionar(@RequestBody LocalizacaoEstoqueInputDTO input) {
        // Cria uma nova localização de estoque por meio do serviço
        LocalizacaoEstoqueOutputDTO novoLocal =  localizacaoEstoqueService.create(input);

        // Gera a URI para o novo recurso (ex: /localizacoes-estoque/{id})
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoLocal.id())
                .toUri()
                ;

        // Retorna resposta HTTP 201 (Created) com o corpo e cabeçalho Location
        return ResponseEntity.created(uri).body(novoLocal);
    }


    /**
     * Endpoint para atualizar uma localização de estoque existente.
     * Método: PUT /localizacoes-estoque/{localizacaoId}
     * @param localizacaoId UUID da localização que será atualizada
     * @param input Dados de atualização (DTO)
     * @return ResponseEntity contendo a localização atualizada
     */
    @PutMapping("/{localizacaoId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<LocalizacaoEstoqueOutputDTO> atualizar(@PathVariable UUID localizacaoId, @RequestBody LocalizacaoEstoqueInputDTO input) {
        return ResponseEntity.ok(localizacaoEstoqueService.update(localizacaoId, input));
    }


    /**
     * Endpoint para excluir uma localização de estoque do sistema.
     * Método: DELETE /localizacoes-estoque/{localizacaoId}
     * @param localizacaoId UUID da localização a ser excluída
     */
    @DeleteMapping("/{localizacaoId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID localizacaoId) {
        localizacaoEstoqueService.delete(localizacaoId);
    }
}
