package com.lumen.cp56.domian.service;


import com.lumen.cp56.domian.dtos.Input.FabricanteInputDTO;
import com.lumen.cp56.domian.dtos.Output.FabricanteOutputDTO;
import com.lumen.cp56.domian.model.Fabricante;
import com.lumen.cp56.domian.repository.FabricanteRepository;
import com.lumen.cp56.mapper.FabricanteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;
    private final FabricanteMapper fabricanteMapper;

    private Fabricante findFabricanteById(UUID id) {
        return fabricanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fabricante não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<FabricanteOutputDTO> findAll() {
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        return fabricanteMapper.toOutputDTOList(fabricantes);
    }

    @Transactional(readOnly = true)
    public FabricanteOutputDTO findById(UUID id) {
        Fabricante fabricante = findFabricanteById(id);
        return fabricanteMapper.toOutputDTO(fabricante);
    }

    public FabricanteOutputDTO create(FabricanteInputDTO dto) {
        Fabricante fabricante = fabricanteMapper.toEntity(dto);
        fabricante.setId(UUID.randomUUID());
        Fabricante savedFabricante = fabricanteRepository.save(fabricante);
        return fabricanteMapper.toOutputDTO(savedFabricante);
    }

    public FabricanteOutputDTO update(UUID id, FabricanteInputDTO dto) {
        Fabricante fabricanteExistente = findFabricanteById(id);
        fabricanteMapper.updateEntityFromDto(dto, fabricanteExistente);
        Fabricante updatedFabricante = fabricanteRepository.save(fabricanteExistente);
        return fabricanteMapper.toOutputDTO(updatedFabricante);
    }

    public void delete(UUID id) {
        Fabricante fabricante = findFabricanteById(id);
        fabricanteRepository.delete(fabricante);
    }
}