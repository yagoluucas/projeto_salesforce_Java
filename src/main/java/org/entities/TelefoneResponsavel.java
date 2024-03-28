package org.entities;

public class TelefoneResponsavel extends _BaseEntities {
    private String numeroTelefone;
    private char principal;
    private char valido;
    private Responsavel responsavel;

    public TelefoneResponsavel() {}
    public TelefoneResponsavel(int id, String numeroTelefone, char principal, char valido, Responsavel responsavel) {
        super(id);
        this.numeroTelefone = numeroTelefone;
        this.principal = principal;
        this.valido = valido;
        this.responsavel = responsavel;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
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

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "TelefoneResponsavel{" +
                "numeroTelefone='" + numeroTelefone + '\'' +
                ", principal=" + principal +
                ", valido=" + valido +
                ", responsavel=" + responsavel +
                "} " + super.toString();
    }
}
