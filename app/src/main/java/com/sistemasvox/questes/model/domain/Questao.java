package com.sistemasvox.questes.model.domain;

public class Questao {
    private String cod, enunciado, dificuldade, referencia;

    public Questao(String cod, String enunciado, String dificuldade, String referencia) {
        super();
        this.cod = cod;
        this.enunciado = enunciado;
        this.setDificuldade(dificuldade);
        this.referencia = referencia;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    @Override
    public String toString() {
        return "Cod: " + cod + "\nENU: " + enunciado + "\nREF: " + referencia + ".";
    }

}

