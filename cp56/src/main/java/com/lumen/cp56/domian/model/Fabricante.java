package com.lumen.cp56.domian.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "fabricantes")
public class Fabricante {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String nomeOficial;
    private String nomeFantasia;
    private String cnpj;
    private String paisOrigem;

    @OneToMany(
            mappedBy = "fabricante",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Reagente> reagentes = new ArrayList<>();

    // Construtor padrão (exigido pelo JPA)
    public Fabricante() {
        // Se o ID for gerado pela aplicação:
        // this.id = UUID.randomUUID();
    }

    // Métodos do UML para gerenciar o relacionamento
    public void adicionarReagente(Reagente reagente) {
        reagentes.add(reagente);
        reagente.setFabricante(this);
    }

    public void removerReagente(Reagente reagente) {
        reagentes.remove(reagente);
        reagente.setFabricante(null);
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeOficial() {
        return nomeOficial;
    }

    public void setNomeOficial(String nomeOficial) {
        this.nomeOficial = nomeOficial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public List<Reagente> getReagentes() {
        return reagentes;
    }

    public void setReagentes(List<Reagente> reagentes) {
        this.reagentes = reagentes;
    }

    // Equals e HashCode (boa prática para entidades)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fabricante that = (Fabricante) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}