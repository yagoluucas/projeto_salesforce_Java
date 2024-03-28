package org.entities;

public class Cliente extends _BaseEntities{
    private String razaoSocial;
    private String login;
    private String senha;
    private PorteEmpresa porteEmpresa;
    private Pais pais;

    public Cliente(){};

    public Cliente(int id, String razaoSocial, String login, String senha, PorteEmpresa porteEmpresa, Pais pais){
        super(id);
        this.razaoSocial = razaoSocial;
        this.login = login;
        this.senha = senha;
        this.porteEmpresa = porteEmpresa;
        this.pais = pais;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PorteEmpresa getPorteEmpresa() {
        return porteEmpresa;
    }

    public void setPorteEmpresa(PorteEmpresa porteEmpresa) {
        this.porteEmpresa = porteEmpresa;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", porteEmpresa=" + porteEmpresa +
                ", pais=" + pais +
                "} " + super.toString();
    }
}
