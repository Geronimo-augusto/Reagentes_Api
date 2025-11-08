package com.lumen.cp56.domian.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "reagentes")
public class Reagente {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String nome;
    private String codigoSku;
    private String lote;
    private LocalDate dataValidade;
    private LocalDate dataRecebimento;
    private Integer quantidadeEstoque;

    @Enumerated(EnumType.STRING)
    private StatusReagente status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacao_estoque_id")
    private LocalizacaoEstoque localizacaoEstoque;

    @OneToMany(
            mappedBy = "reagente",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();

    // Construtor padrão
    public Reagente() {
    }

    // Métodos do UML
    public void movimentarEstoque(MovimentacaoEstoque movimentacao) {
        this.movimentacoes.add(movimentacao);
        movimentacao.setReagente(this);
        // Lógica de negócio (Ex: atualizar quantidadeEstoque) iria aqui.
        // Por ex: this.quantidadeEstoque += movimentacao.getQuantidadeMovimentada();
    }

    public boolean estaVencido() {
        if (this.dataValidade == null) {
            return false;
        }
        return LocalDate.now().isAfter(this.dataValidade);
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoSku() {
        return codigoSku;
    }

    public void setCodigoSku(String codigoSku) {
        this.codigoSku = codigoSku;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDate dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public StatusReagente getStatus() {
        return status;
    }

    public void setStatus(StatusReagente status) {
        this.status = status;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public LocalizacaoEstoque getLocalizacaoEstoque() {
        return localizacaoEstoque;
    }

    public void setLocalizacaoEstoque(LocalizacaoEstoque localizacaoEstoque) {
        this.localizacaoEstoque = localizacaoEstoque;
    }

    public List<MovimentacaoEstoque> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoEstoque> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reagente reagente = (Reagente) o;
        return Objects.equals(id, reagente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}