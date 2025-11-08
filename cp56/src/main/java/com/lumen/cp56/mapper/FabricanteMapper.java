package com.lumen.cp56.mapper;

import com.lumen.cp56.domian.dtos.Input.FabricanteInputDTO;
import com.lumen.cp56.domian.dtos.Output.FabricanteOutputDTO;
import com.lumen.cp56.domian.model.Fabricante;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FabricanteMapper {

    public FabricanteOutputDTO toOutputDTO(Fabricante fabricante) {
        if (fabricante == null) return null;
        return new FabricanteOutputDTO(
                fabricante.getId(),
                fabricante.getNomeOficial(),
                fabricante.getNomeFantasia(),
                fabricante.getCnpj(),
                fabricante.getPaisOrigem()
        );
    }

    public List<FabricanteOutputDTO> toOutputDTOList(List<Fabricante> fabricantes) {
        return fabricantes.stream()
                .map(this::toOutputDTO)
                .collect(Collectors.toList());
    }

    public Fabricante toEntity(FabricanteInputDTO dto) {
        if (dto == null) return null;
        Fabricante fabricante = new Fabricante();
        fabricante.setNomeOficial(dto.nomeOficial());
        fabricante.setNomeFantasia(dto.nomeFantasia());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setPaisOrigem(dto.paisOrigem());
        return fabricante;
    }

    public void updateEntityFromDto(FabricanteInputDTO dto, Fabricante fabricante) {
        if (dto == null || fabricante == null) return;
        fabricante.setNomeOficial(dto.nomeOficial());
        fabricante.setNomeFantasia(dto.nomeFantasia());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setPaisOrigem(dto.paisOrigem());
    }
}