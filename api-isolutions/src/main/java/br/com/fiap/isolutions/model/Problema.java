package br.com.fiap.isolutions.model;

public class Problema {

    private int idProblema;
    private String descricao;
    private String tipo;
    private int gravidade;
    private int idVeiculo;

    public Problema(int idProblema, String descricao, String tipo, int gravidade, int idVeiculo) {
        this.idProblema = idProblema;
        this.descricao = descricao;
        this.tipo = tipo;
        this.gravidade = gravidade;
        this.idVeiculo = idVeiculo;
    }

    public Problema (){}

    public int getIdProblema() { return idProblema; }
    public void setIdProblema(int idProblema) { this.idProblema = idProblema; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getGravidade() { return gravidade; }
    public void setGravidade(int gravidade) { this.gravidade = gravidade; }

    public int getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(int idVeiculo) { this.idVeiculo = idVeiculo; }
}

