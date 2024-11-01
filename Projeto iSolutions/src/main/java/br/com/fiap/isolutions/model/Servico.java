package br.com.fiap.isolutions.model;

public class Servico {

    private int idServico;
    private String data;
    private double custo;
    private String horarioServico;
    private String statusServico;

    public Servico( int idServico, String data, double custo, String horarioServico, String statusServico) {
        this.idServico = idServico;
        this.data = data;
        this.custo = custo;
        this.horarioServico = horarioServico;
        this.statusServico = statusServico;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public String getHorarioServico() {
        return horarioServico;
    }

    public void setHorarioServico(String horarioServico) {
        this.horarioServico = horarioServico;
    }

    public String getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(String statusServico) {
        this.statusServico = statusServico;
    }

    @Override
    public String toString() {
        return "\n ID= " + idServico +
                "\n Data Serviço= " + data +
                "\n Custo= " + custo +
                "\n Horário Serviço= " + horarioServico +
                "\n Status Serviço= " + statusServico    ;
    }
}


