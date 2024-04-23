package org.entities;
public class Suporte extends _BaseEntity {
    private String nomeEmpresa;
    private String nomePessoa;
    private String sobrenomePessoa;
    private String descricao;
    private Pais pais;

    public Suporte() {}

    public Suporte(int id, String nomeEmpresa, String nomePessoa, String sobrenomePessoa, String descricao, Pais pais) {
        super(id);
        this.nomeEmpresa = nomeEmpresa;
        this.nomePessoa = nomePessoa;
        this.sobrenomePessoa = sobrenomePessoa;
        this.descricao = descricao;
        this.pais = pais;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getSobrenomePessoa() {
        return sobrenomePessoa;
    }

    public void setSobrenomePessoa(String sobrenomePessoa) {
        this.sobrenomePessoa = sobrenomePessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Suporte{" +
                "nomeEmpresa='" + nomeEmpresa + '\'' +
                ", nomePessoa='" + nomePessoa + '\'' +
                ", sobrenomePessoa='" + sobrenomePessoa + '\'' +
                ", descricao='" + descricao + '\'' +
                ", pais=" + pais +
                "} " + super.toString();
    }
}
