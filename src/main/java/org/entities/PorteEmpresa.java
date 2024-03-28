package org.entities;

public class PorteEmpresa extends _BaseEntities {
    private String descricao;

    public PorteEmpresa(){}

    public PorteEmpresa(int id, String descricao) {
        super(id);
        this.descricao = descricao;
    }

    public PorteEmpresa(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    @Override
    public String toString() {
        return "PorteEmpresa{" +
                "descricao='" + descricao + '\'' +
                "} " + super.toString();
    }
}
