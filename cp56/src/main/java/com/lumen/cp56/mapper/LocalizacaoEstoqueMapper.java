package com.lumen.cp56.mapper;


import com.lumen.cp56.domian.dtos.Input.LocalizacaoEstoqueInputDTO;
import com.lumen.cp56.domian.dtos.Output.LocalizacaoEstoqueOutputDTO;
import com.lumen.cp56.domian.model.LocalizacaoEstoque;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocalizacaoEstoqueMapper {

    public LocalizacaoEstoqueOutputDTO toOutputDTO(LocalizacaoEstoque localizacao) {
        if (localizacao == null) return null;
        return new LocalizacaoEstoqueOutputDTO(
                localizacao.getId(),
                localizacao.getCodigoLocal(),
                localizacao.getDescricao(),
                localizacao.getSetor(),
                localizacao.getFaixaTemperaturaNominal(),
                localizacao.getTipo()
        );
    }

    public List<LocalizacaoEstoqueOutputDTO> toOutputDTOList(List<LocalizacaoEstoque> localizacoes) {
        return localizacoes.stream()
                .map(this::toOutputDTO)
                .collect(Collectors.toList());
    }

    public LocalizacaoEstoque toEntity(LocalizacaoEstoqueInputDTO dto) {
        if (dto == null) return null;
        LocalizacaoEstoque localizacao = new LocalizacaoEstoque();
        localizacao.setCodigoLocal(dto.codigoLocal());
        localizacao.setDescricao(dto.descricao());
        localizacao.setSetor(dto.setor());
        localizacao.setFaixaTemperaturaNominal(dto.faixaTemperaturaNominal());
        localizacao.setTipo(dto.tipo());
        return localizacao;
    }

    public void updateEntityFromDto(LocalizacaoEstoqueInputDTO dto, LocalizacaoEstoque localizacao) {
        if (dto == null || localizacao == null) return;
        localizacao.setCodigoLocal(dto.codigoLocal());
        localizacao.setDescricao(dto.descricao());
        localizacao.setSetor(dto.setor());
        localizacao.setFaixaTemperaturaNominal(dto.faixaTemperaturaNominal());
        localizacao.setTipo(dto.tipo());
    }
}