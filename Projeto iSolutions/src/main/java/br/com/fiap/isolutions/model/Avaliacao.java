package br.com.fiap.isolutions.model;

import java.util.Date;

public class Avaliacao {

    private int idAvaliacao;
    private String data;
    private String classificacao;
    private String comentario;

    public Avaliacao(int idAvaliacao, String data, String classificacao, String comentario) {
        this.idAvaliacao = idAvaliacao;
        this.data = data;
        this.classificacao = classificacao;
        this.comentario = comentario;
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
