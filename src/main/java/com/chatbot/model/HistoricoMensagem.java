package com.chatbot.model;

import java.time.LocalDateTime;

public class HistoricoMensagem {
    private Integer idMensagem;
    private Integer idLog;
    private String origemMensagem;
    private String conteudo;
    private LocalDateTime dataHora;

    public HistoricoMensagem() {}
    public HistoricoMensagem(Integer idMensagem, Integer idLog, String origemMensagem, String conteudo, LocalDateTime dataHora) {
        this.idMensagem = idMensagem;
        this.idLog = idLog;
        this.origemMensagem = origemMensagem;
        this.conteudo = conteudo;
        this.dataHora = dataHora;
    }

    public Integer getIdMensagem() { return idMensagem; }
    public void setIdMensagem(Integer idMensagem) { this.idMensagem = idMensagem; }
    public Integer getIdLog() { return idLog; }
    public void setIdLog(Integer idLog) { this.idLog = idLog; }
    public String getOrigemMensagem() { return origemMensagem; }
    public void setOrigemMensagem(String origemMensagem) { this.origemMensagem = origemMensagem; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public java.time.LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(java.time.LocalDateTime dataHora) { this.dataHora = dataHora; }

    @Override
    public String toString() {
        return "HistoricoMensagem{id=" + idMensagem + ", idLog=" + idLog + ", origem='" + origemMensagem + "', conteudo='" + conteudo + "', dataHora=" + dataHora + "}";
    }
}