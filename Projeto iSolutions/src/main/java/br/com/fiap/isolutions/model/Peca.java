package br.com.fiap.isolutions.model;

public class Peca {

    private int idPeca;
    private String nome;
    private String numeroSerie;
    private String fabricante;

    public Peca(int idPeca, String nome, String numeroSerie, String fabricante) {
        this.idPeca = idPeca;
        this.nome = nome;
        this.numeroSerie = numeroSerie;
        this.fabricante = fabricante;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
