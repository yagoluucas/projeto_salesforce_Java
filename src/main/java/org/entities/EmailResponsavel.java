package org.entities;

public class EmailResponsavel extends _BaseEntities {
    private String email;
    private char principal;
    private Responsavel responsavel;
    private char valido;

    public EmailResponsavel(){}

    public EmailResponsavel(int id, String email, char principal, Responsavel responsavel, char valido) {
        super(id);
        this.email = email;
        this.principal = principal;
        this.responsavel = responsavel;
        this.valido = valido;
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

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public char getValido() {
        return valido;
    }

    public void setValido(char valido) {
        this.valido = valido;
    }

    @Override
    public String toString() {
        return "EmailResponsavel{" +
                "email='" + email + '\'' +
                ", principal=" + principal +
                ", responsavel=" + responsavel +
                ", valido=" + valido +
                "} " + super.toString();
    }
}
