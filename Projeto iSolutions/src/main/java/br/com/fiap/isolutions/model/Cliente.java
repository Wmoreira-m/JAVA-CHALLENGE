package br.com.fiap.isolutions.model;

public class Cliente {
    private String nome;
    private String cpf;
    private int idCliente;
    private String login;
    private String senha;

    public Cliente(String nome, String cpf, int idCliente, String login, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.idCliente = idCliente;
        this.login = login;
        this.senha = senha;
    }

    public Cliente(){}

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return "\n nome= " + nome +
                "\n cpf= " + cpf +
                "\n idCliente= " + idCliente +
                "\n login= " + login +
                "\n senha= " + senha ;
    }
}

