package com.chatbot.model;

public class Avaliacao {

    private Integer idAvaliacao;
    private Integer idLog;
    private Integer nota;
    private String comentario;

    public Avaliacao() {}

    public Avaliacao(Integer idAvaliacao, Integer idLog, Integer nota, String comentario) {

        this.idAvaliacao = idAvaliacao;
        this.idLog = idLog;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Integer getIdAvaliacao() { return idAvaliacao; }
    public void setIdAvaliacao(Integer idAvaliacao) { this.idAvaliacao = idAvaliacao; }
    public Integer getIdLog() { return idLog; }
    public void setIdLog(Integer idLog) { this.idLog = idLog; }
    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    @Override
    public String toString() {
        return "Avaliacao{id=" + idAvaliacao + ", idLog=" + idLog + ", nota=" + nota + ", comentario='" + comentario + "'}";
    }
}