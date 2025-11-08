package com.lumen.cp56.mapper;


import com.lumen.cp56.domian.dtos.Input.MovimentacaoEstoqueInputDTO;
import com.lumen.cp56.domian.dtos.Output.MovimentacaoEstoqueOutputDTO;
import com.lumen.cp56.domian.model.MovimentacaoEstoque;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MovimentacaoEstoqueMapper {

    public MovimentacaoEstoqueOutputDTO toOutputDTO(MovimentacaoEstoque movimentacao) {
        if (movimentacao == null) return null;
        UUID reagenteId = (movimentacao.getReagente() != null) ? movimentacao.getReagente().getId() : null;

        return new MovimentacaoEstoqueOutputDTO(
                movimentacao.getId(),
                movimentacao.getObservacao(),
                movimentacao.getQuantidadeMovimentada(),
                movimentacao.getDataHoraMovimentacao(),
                movimentacao.getDataBalanco(),
                movimentacao.getTipo(),
                reagenteId
        );
    }

    public List<MovimentacaoEstoqueOutputDTO> toOutputDTOList(List<MovimentacaoEstoque> movimentacoes) {
        return movimentacoes.stream()
                .map(this::toOutputDTO)
                .collect(Collectors.toList());
    }

    public MovimentacaoEstoque toEntity(MovimentacaoEstoqueInputDTO dto) {
        if (dto == null) return null;
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setObservacao(dto.observacao());
        movimentacao.setQuantidadeMovimentada(dto.quantidadeMovimentada());
        movimentacao.setDataBalanco(dto.dataBalanco());
        movimentacao.setTipo(dto.tipo());
        return movimentacao;
    }
}