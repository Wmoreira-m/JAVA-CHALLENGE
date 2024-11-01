package br.com.fiap.isolutions.model;
public class Mecanica {

    private int idMecanica;
    private String nome;
    private String telefone;
    private String cep;

    public Mecanica(int idMecanica, String nome, String telefone, String cep) {
        this.idMecanica = idMecanica;
        this.nome = nome;
        this.telefone = telefone;
        this.cep = cep;
    }

    public int getIdMecanica() {
        return idMecanica;
    }

    public void setIdMecanica(int idMecanica) {
        this.idMecanica = idMecanica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
