package br.com.fiap.isolutions.model;

public class Marca {

    private int idMarca;
    private String nome;
    private String paisOrigem;
    private String anoFabricacao;

    public Marca(int idMarca, String nome) {
        this.idMarca = idMarca;
        this.nome = nome;
    }


    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
}
