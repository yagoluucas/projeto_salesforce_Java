package org.entities;

public class EmailCorporativo extends _BaseEntities {
    private String email;
    private char principal;
    private char valido;
    private Cliente cliente;
    private Responsavel responsavel;
    public EmailCorporativo() {}
    public EmailCorporativo(int id, String email, char principal, char valido, Cliente cliente, Responsavel responsavel) {
        super(id);
        this.email = email;
        this.principal = principal;
        this.valido = valido;
        this.cliente = cliente;
        this.responsavel = responsavel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getPrincipal() {
        return principal;
    }

    public void setPrincipal(char principal) {
        this.principal = principal;
    }

    public char getValido() {
        return valido;
    }

    public void setValido(char valido) {
        this.valido = valido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "EmailCorporativo{" +
                "email='" + email + '\'' +
                ", principal=" + principal +
                ", valido=" + valido +
                ", cliente=" + cliente +
                ", responsavel=" + responsavel +
                "} " + super.toString();
    }
}
