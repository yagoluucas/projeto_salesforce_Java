package org.entities;

public class Pais extends _BaseEntities {
    private String Descricao;

    public Pais(){}

    public Pais(int id, String pais) {
        super(id);
        Descricao = pais;
    }

    public Pais (String pais) {
        Descricao = pais;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "Pais='" + Descricao + '\'' +
                "} " + super.toString();
    }
}
