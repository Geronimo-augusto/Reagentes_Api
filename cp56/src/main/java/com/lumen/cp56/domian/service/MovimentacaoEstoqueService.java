package com.lumen.cp56.domian.service;


import com.lumen.cp56.domian.dtos.Input.MovimentacaoEstoqueInputDTO;
import com.lumen.cp56.domian.dtos.Output.MovimentacaoEstoqueOutputDTO;
import com.lumen.cp56.domian.model.MovimentacaoEstoque;
import com.lumen.cp56.domian.model.Reagente;
import com.lumen.cp56.domian.repository.MovimentacaoEstoqueRepository;
import com.lumen.cp56.domian.repository.ReagenteRepository;
import com.lumen.cp56.mapper.MovimentacaoEstoqueMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final ReagenteRepository reagenteRepository;
    private final MovimentacaoEstoqueMapper movimentacaoEstoqueMapper;

    private Reagente findReagenteById(UUID id) {
        return reagenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reagente não encontrado com ID: " + id));
    }

    private MovimentacaoEstoque findMovimentacaoById(UUID id) {
        return movimentacaoEstoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movimentação não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoEstoqueOutputDTO> findAllByReagenteId(UUID reagenteId) {
        findReagenteById(reagenteId); // Valida se o reagente existe
        List<MovimentacaoEstoque> movimentacoes = movimentacaoEstoqueRepository.findByReagenteIdOrderByDataHoraMovimentacaoDesc(reagenteId);
        return movimentacaoEstoqueMapper.toOutputDTOList(movimentacoes);
    }

    public MovimentacaoEstoqueOutputDTO create(UUID reagenteId, MovimentacaoEstoqueInputDTO dto) {
        Reagente reagente = findReagenteById(reagenteId);
        MovimentacaoEstoque movimentacao = movimentacaoEstoqueMapper.toEntity(dto);
        movimentacao.setId(UUID.randomUUID());
        movimentacao.setReagente(reagente);
        movimentacao.setDataHoraMovimentacao(LocalDateTime.now());

        atualizarEstoqueReagente(reagente, dto.quantidadeMovimentada());

        MovimentacaoEstoque savedMovimentacao = movimentacaoEstoqueRepository.save(movimentacao);
        return movimentacaoEstoqueMapper.toOutputDTO(savedMovimentacao);
    }

    public void delete(UUID id) {
        MovimentacaoEstoque movimentacao = findMovimentacaoById(id);
        Reagente reagente = movimentacao.getReagente();

        // Reverte a quantidade
        atualizarEstoqueReagente(reagente, -movimentacao.getQuantidadeMovimentada());

        movimentacaoEstoqueRepository.delete(movimentacao);
    }

    private void atualizarEstoqueReagente(Reagente reagente, int quantidade) {
        int estoqueAtual = reagente.getQuantidadeEstoque() != null ? reagente.getQuantidadeEstoque() : 0;
        int novoEstoque = estoqueAtual + quantidade;

        if (novoEstoque < 0) {
            throw new IllegalStateException("Estoque não pode ficar negativo.");
        }
        reagente.setQuantidadeEstoque(novoEstoque);
    }
}