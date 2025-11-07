package com.chatbot.model;

public class Categoria {
    private Integer idCategoria;
    private String nomeCategoria;
    private String descricao;

    public Categoria() {}
    public Categoria(Integer idCategoria, String nomeCategoria, String descricao) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }

    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }
    public String getNomeCategoria() { return nomeCategoria; }
    public void setNomeCategoria(String nomeCategoria) { this.nomeCategoria = nomeCategoria; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "Categoria{id=" + idCategoria + ", nome='" + nomeCategoria + "', desc='" + descricao + "'}";
    }
}