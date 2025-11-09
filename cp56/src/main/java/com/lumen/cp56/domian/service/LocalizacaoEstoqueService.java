package com.lumen.cp56.domian.service;


import com.lumen.cp56.domian.dtos.Input.LocalizacaoEstoqueInputDTO;
import com.lumen.cp56.domian.dtos.Output.LocalizacaoEstoqueOutputDTO;
import com.lumen.cp56.domian.model.LocalizacaoEstoque;
import com.lumen.cp56.domian.repository.LocalizacaoEstoqueRepository;
import com.lumen.cp56.mapper.LocalizacaoEstoqueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service

@RequiredArgsConstructor
public class LocalizacaoEstoqueService {

    private final LocalizacaoEstoqueRepository localizacaoEstoqueRepository;
    private final LocalizacaoEstoqueMapper localizacaoEstoqueMapper;

    private LocalizacaoEstoque findLocalizacaoById(UUID id) {
        return localizacaoEstoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada com ID: " + id));
    }


    public List<LocalizacaoEstoqueOutputDTO> findAll() {
        List<LocalizacaoEstoque> localizacoes = localizacaoEstoqueRepository.findAll();
        return localizacaoEstoqueMapper.toOutputDTOList(localizacoes);
    }


    public LocalizacaoEstoqueOutputDTO findById(UUID id) {
        LocalizacaoEstoque localizacao = findLocalizacaoById(id);
        return localizacaoEstoqueMapper.toOutputDTO(localizacao);
    }

    public LocalizacaoEstoqueOutputDTO create(LocalizacaoEstoqueInputDTO dto) {
        LocalizacaoEstoque localizacao = localizacaoEstoqueMapper.toEntity(dto);
        localizacao.setId(UUID.randomUUID());
        LocalizacaoEstoque savedLocalizacao = localizacaoEstoqueRepository.save(localizacao);
        return localizacaoEstoqueMapper.toOutputDTO(savedLocalizacao);
    }

    public LocalizacaoEstoqueOutputDTO update(UUID id, LocalizacaoEstoqueInputDTO dto) {
        LocalizacaoEstoque localizacaoExistente = findLocalizacaoById(id);
        localizacaoEstoqueMapper.updateEntityFromDto(dto, localizacaoExistente);
        LocalizacaoEstoque updatedLocalizacao = localizacaoEstoqueRepository.save(localizacaoExistente);
        return localizacaoEstoqueMapper.toOutputDTO(updatedLocalizacao);
    }

    public void delete(UUID id) {
        LocalizacaoEstoque localizacao = findLocalizacaoById(id);
        localizacaoEstoqueRepository.delete(localizacao);
    }
}