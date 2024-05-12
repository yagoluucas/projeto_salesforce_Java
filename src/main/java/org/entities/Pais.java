package org.entities;

public class Pais extends _BaseEntity {
    private String descricao;
    public Pais(){}

    public Pais(int id, String pais) {
        super(id);
        descricao = pais;
    }

    public Pais (String pais) {
        descricao = pais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "Pais='" + descricao + '\'' +
                "} " + super.toString();
    }
}
