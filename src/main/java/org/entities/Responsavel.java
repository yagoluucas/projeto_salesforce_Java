package org.entities;

public class Responsavel extends _BaseEntities {
    private String nome;
    private String sobrenome;
    private String cargo;
    private Cliente cliente;

    public Responsavel() {}

    public Responsavel(int id, String nome, String sobrenome, String cargo, Cliente cliente) {
        super(id);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.cliente = cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Responsavel{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", cliente=" + cliente +
                "} " + super.toString();
    }
}
