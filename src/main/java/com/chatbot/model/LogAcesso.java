package com.chatbot.model;

import java.time.LocalDateTime;

public class LogAcesso {
    private Integer idLog;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private Integer duracao;

    public LogAcesso() {}
    public LogAcesso(Integer idLog, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, Integer duracao) {
        this.idLog = idLog;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.duracao = duracao;
    }

    public Integer getIdLog() { return idLog; }
    public void setIdLog(Integer idLog) { this.idLog = idLog; }
    public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) { this.dataHoraInicio = dataHoraInicio; }
    public LocalDateTime getDataHoraFim() { return dataHoraFim; }
    public void setDataHoraFim(LocalDateTime dataHoraFim) { this.dataHoraFim = dataHoraFim; }
    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }

    @Override
    public String toString() {
        return "LogAcesso{id=" + idLog + ", inicio=" + dataHoraInicio + ", fim=" + dataHoraFim + ", duracao=" + duracao + "}";
    }
}