package com.lumen.cp56.domian.service;


import com.lumen.cp56.domian.dtos.Input.ReagenteInputDTO;
import com.lumen.cp56.domian.dtos.Output.ReagenteOutputDTO;
import com.lumen.cp56.domian.model.Fabricante;
import com.lumen.cp56.domian.model.LocalizacaoEstoque;
import com.lumen.cp56.domian.model.Reagente;
import com.lumen.cp56.domian.repository.FabricanteRepository;
import com.lumen.cp56.domian.repository.LocalizacaoEstoqueRepository;
import com.lumen.cp56.domian.repository.ReagenteRepository;
import com.lumen.cp56.mapper.ReagenteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ReagenteService {

    private final ReagenteRepository reagenteRepository;
    private final FabricanteRepository fabricanteRepository;
    private final LocalizacaoEstoqueRepository localizacaoEstoqueRepository;
    private final ReagenteMapper reagenteMapper;

    private Reagente findReagenteById(UUID id) {
        return reagenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reagente não encontrado com ID: " + id));
    }

    private Fabricante findFabricanteById(UUID id) {
        return fabricanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fabricante não encontrado com ID: " + id));
    }

    private LocalizacaoEstoque findLocalizacaoById(UUID id) {
        return localizacaoEstoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Localização não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<ReagenteOutputDTO> findAll() {
        List<Reagente> reagentes = reagenteRepository.findAll();
        return reagenteMapper.toOutputDTOList(reagentes);
    }

    @Transactional(readOnly = true)
    public ReagenteOutputDTO findById(UUID id) {
        Reagente reagente = findReagenteById(id);
        return reagenteMapper.toOutputDTO(reagente);
    }

    public ReagenteOutputDTO create(ReagenteInputDTO dto) {
        Fabricante fabricante = findFabricanteById(dto.fabricanteId());
        LocalizacaoEstoque localizacao = findLocalizacaoById(dto.localizacaoEstoqueId());

        Reagente reagente = reagenteMapper.toEntity(dto);
        reagente.setId(UUID.randomUUID());
        reagente.setFabricante(fabricante);
        reagente.setLocalizacaoEstoque(localizacao);

        Reagente savedReagente = reagenteRepository.save(reagente);
        return reagenteMapper.toOutputDTO(savedReagente);
    }

    public ReagenteOutputDTO update(UUID id, ReagenteInputDTO dto) {
        Reagente reagenteExistente = findReagenteById(id);

        if (dto.fabricanteId() != null && !reagenteExistente.getFabricante().getId().equals(dto.fabricanteId())) {
            Fabricante fabricante = findFabricanteById(dto.fabricanteId());
            reagenteExistente.setFabricante(fabricante);
        }
        if (dto.localizacaoEstoqueId() != null && !reagenteExistente.getLocalizacaoEstoque().getId().equals(dto.localizacaoEstoqueId())) {
            LocalizacaoEstoque localizacao = findLocalizacaoById(dto.localizacaoEstoqueId());
            reagenteExistente.setLocalizacaoEstoque(localizacao);
        }

        reagenteMapper.updateEntityFromDto(dto, reagenteExistente);
        Reagente updatedReagente = reagenteRepository.save(reagenteExistente);
        return reagenteMapper.toOutputDTO(updatedReagente);
    }

    public void delete(UUID id) {
        Reagente reagente = findReagenteById(id);
        reagenteRepository.delete(reagente);
    }
}