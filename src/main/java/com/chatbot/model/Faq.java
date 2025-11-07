package com.chatbot.model;

public class Faq {
    private Integer idFaq;
    private String pergunta;
    private String resposta;
    private Integer idCategoria;

    public Faq() {}
    public Faq(Integer idFaq, String pergunta, String resposta, Integer idCategoria) {
        this.idFaq = idFaq;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.idCategoria = idCategoria;
    }

    public Integer getIdFaq() { return idFaq; }
    public void setIdFaq(Integer idFaq) { this.idFaq = idFaq; }
    public String getPergunta() { return pergunta; }
    public void setPergunta(String pergunta) { this.pergunta = pergunta; }
    public String getResposta() { return resposta; }
    public void setResposta(String resposta) { this.resposta = resposta; }
    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }

    @Override
    public String toString() {
        return "Faq{id=" + idFaq + ", pergunta='" + pergunta + "', resposta='" + resposta + "', idCategoria=" + idCategoria + "}";
    }
}