package com.lumen.cp56.mapper;


import com.lumen.cp56.domian.dtos.Input.ReagenteInputDTO;
import com.lumen.cp56.domian.dtos.Output.FabricanteOutputDTO;
import com.lumen.cp56.domian.dtos.Output.LocalizacaoEstoqueOutputDTO;
import com.lumen.cp56.domian.dtos.Output.ReagenteOutputDTO;
import com.lumen.cp56.domian.model.Reagente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReagenteMapper {

    private final FabricanteMapper fabricanteMapper;
    private final LocalizacaoEstoqueMapper localizacaoEstoqueMapper;

    @Autowired
    public ReagenteMapper(FabricanteMapper fabricanteMapper, LocalizacaoEstoqueMapper localizacaoEstoqueMapper) {
        this.fabricanteMapper = fabricanteMapper;
        this.localizacaoEstoqueMapper = localizacaoEstoqueMapper;
    }

    public ReagenteOutputDTO toOutputDTO(Reagente reagente) {
        if (reagente == null) return null;

        FabricanteOutputDTO fabricanteDTO = fabricanteMapper.toOutputDTO(reagente.getFabricante());
        LocalizacaoEstoqueOutputDTO localizacaoDTO = localizacaoEstoqueMapper.toOutputDTO(reagente.getLocalizacaoEstoque());

        return new ReagenteOutputDTO(
                reagente.getId(),
                reagente.getNome(),
                reagente.getCodigoSku(),
                reagente.getLote(),
                reagente.getDataValidade(),
                reagente.getDataRecebimento(),
                reagente.getQuantidadeEstoque(),
                reagente.getStatus(),
                reagente.estaVencido(),
                fabricanteDTO,
                localizacaoDTO
        );
    }

    public List<ReagenteOutputDTO> toOutputDTOList(List<Reagente> reagentes) {
        return reagentes.stream()
                .map(this::toOutputDTO)
                .collect(Collectors.toList());
    }

    public Reagente toEntity(ReagenteInputDTO dto) {
        if (dto == null) return null;
        Reagente reagente = new Reagente();
        reagente.setNome(dto.nome());
        reagente.setCodigoSku(dto.codigoSku());
        reagente.setLote(dto.lote());
        reagente.setDataValidade(dto.dataValidade());
        reagente.setDataRecebimento(dto.dataRecebimento());
        reagente.setQuantidadeEstoque(dto.quantidadeEstoque());
        reagente.setStatus(dto.status());
        return reagente;
    }

    public void updateEntityFromDto(ReagenteInputDTO dto, Reagente reagente) {
        if (dto == null || reagente == null) return;
        reagente.setNome(dto.nome());
        reagente.setCodigoSku(dto.codigoSku());
        reagente.setLote(dto.lote());
        reagente.setDataValidade(dto.dataValidade());
        reagente.setDataRecebimento(dto.dataRecebimento());
        reagente.setQuantidadeEstoque(dto.quantidadeEstoque());
        reagente.setStatus(dto.status());
    }
}