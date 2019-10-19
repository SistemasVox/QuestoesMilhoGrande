package com.sistemasvox.questes.model.domain;

public class Alternativa {
    private String cod, cod_q, classificacao, resposta, justificativa;

    public Alternativa(String cod, String cod_q, String classificacao, String resposta, String justificativa) {
        super();
        this.cod = cod;
        this.cod_q = cod_q;
        this.classificacao = classificacao;
        this.resposta = resposta;
        this.justificativa = justificativa;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCod_q() {
        return cod_q;
    }

    public void setCod_q(String cod_q) {
        this.cod_q = cod_q;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "Alternativa{" +
                "cod='" + cod + '\'' +
                ", cod_q='" + cod_q + '\'' +
                ", classificacao='" + classificacao + '\'' +
                ", resposta='" + resposta + '\'' +
                ", justificativa='" + justificativa + '\'' +
                '}';
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

}
