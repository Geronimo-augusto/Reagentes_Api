package com.lumen.cp56.domian.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa um Fabricante de reagentes.
 * Esta classe é uma entidade de domínio que poderá ser mapeada no banco pelo JPA.
 *
 * Cada Fabricante pode produzir um ou mais Reagentes (relacionamento 1:N).
 */
public class Fabricante {

    /**
     * Identificador único do fabricante.
     * Pode ser gerado pela aplicação ou pelo provedor JPA, dependendo da estratégia adotada.
     */
    private UUID id;

    /** Nome jurídico do fabricante (como consta em registro oficial). */
    private String nomeOficial;

    /** Nome fantasia ou nome comercial pelo qual o fabricante é conhecido no mercado. */
    private String nomeFantasia;

    /** CNPJ do fabricante (para entidades brasileiras). */
    private String cnpj;

    /** País onde o fabricante está localizado ou foi fundado. */
    private String paisOrigem;

    /**
     * Lista de reagentes produzidos pelo fabricante.
     * Representa o lado "um" do relacionamento 1:N com Reagente.
     */
    private List<Reagente> reagentes = new ArrayList<>();

    /**
     * Construtor padrão.
     * Necessário para uso pelo JPA e frameworks de serialização.
     */
    public Fabricante() {
        // Caso o ID seja gerado na aplicação, descomente:
        // this.id = UUID.randomUUID();
    }

    /**
     * Associa um reagente a este fabricante.
     * Mantém a integridade bidirecional do relacionamento.
     *
     * @param reagente reagente a ser vinculado
     */
    public void adicionarReagente(Reagente reagente) {
        reagentes.add(reagente);
        reagente.setFabricante(this);
    }

    /**
     * Remove a associação entre o reagente e este fabricante.
     * Mantém a integridade bidirecional do relacionamento.
     *
     * @param reagente reagente a ser desvinculado
     */
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

    /**
     * Equals e hashCode baseados apenas no ID.
     * Importante para evitar problemas em coleções e comparações entre entidades.
     */
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
