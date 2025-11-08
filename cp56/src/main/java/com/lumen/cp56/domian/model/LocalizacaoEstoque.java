package com.lumen.cp56.domian.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class LocalizacaoEstoque {


    private UUID id;

    private String codigoLocal;
    private String descricao;
    private String setor;
    private String faixaTemperaturaNominal;


    private TipoLocalizacaoEstoque tipo;


    private List<Reagente> reagentes = new ArrayList<>();

    // Construtor padrão
    public LocalizacaoEstoque() {
    }

    // Métodos do UML para gerenciar o relacionamento
    public void adicionarReagente(Reagente reagente) {
        reagentes.add(reagente);
        reagente.setLocalizacaoEstoque(this);
    }

    public void removerReagente(Reagente reagente) {
        reagentes.remove(reagente);
        reagente.setLocalizacaoEstoque(null);
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getFaixaTemperaturaNominal() {
        return faixaTemperaturaNominal;
    }

    public void setFaixaTemperaturaNominal(String faixaTemperaturaNominal) {
        this.faixaTemperaturaNominal = faixaTemperaturaNominal;
    }

    public TipoLocalizacaoEstoque getTipo() {
        return tipo;
    }

    public void setTipo(TipoLocalizacaoEstoque tipo) {
        this.tipo = tipo;
    }

    public List<Reagente> getReagentes() {
        return reagentes;
    }

    public void setReagentes(List<Reagente> reagentes) {
        this.reagentes = reagentes;
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalizacaoEstoque that = (LocalizacaoEstoque) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}