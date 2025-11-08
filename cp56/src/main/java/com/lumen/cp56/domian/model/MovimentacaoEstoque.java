package com.lumen.cp56.domian.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


public class MovimentacaoEstoque {


    private UUID id;

    private String observacao;
    private Integer quantidadeMovimentada;
    private LocalDateTime dataHoraMovimentacao;
    private LocalDate dataBalanco;


    private TipoMovimentacao tipo;


    private Reagente reagente;

    // Construtor padrão
    public MovimentacaoEstoque() {
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getQuantidadeMovimentada() {
        return quantidadeMovimentada;
    }

    public void setQuantidadeMovimentada(Integer quantidadeMovimentada) {
        this.quantidadeMovimentada = quantidadeMovimentada;
    }

    public LocalDateTime getDataHoraMovimentacao() {
        return dataHoraMovimentacao;
    }

    public void setDataHoraMovimentacao(LocalDateTime dataHoraMovimentacao) {
        this.dataHoraMovimentacao = dataHoraMovimentacao;
    }

    public LocalDate getDataBalanco() {
        return dataBalanco;
    }

    public void setDataBalanco(LocalDate dataBalanco) {
        this.dataBalanco = dataBalanco;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public Reagente getReagente() {
        return reagente;
    }

    public void setReagente(Reagente reagente) {
        this.reagente = reagente;
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentacaoEstoque that = (MovimentacaoEstoque) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}