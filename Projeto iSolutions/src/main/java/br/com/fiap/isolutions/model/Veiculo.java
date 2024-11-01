package br.com.fiap.isolutions.model;

public class Veiculo {

    private int idVeiculo;
    private String modelo;
    private String ano;
    private String placa;
    private int idMarca;
    private int idCliente;

    public Veiculo(int idVeiculo, String modelo, String ano, String placa, int idMarca, int idCliente) {
        this.idVeiculo = idVeiculo;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.idMarca = idMarca;
        this.idCliente = idCliente;
    }

    public Veiculo(){}

    public int getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(int idVeiculo) { this.idVeiculo = idVeiculo; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getAno() { return ano; }
    public void setAno(String ano) { this.ano = ano; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
}

