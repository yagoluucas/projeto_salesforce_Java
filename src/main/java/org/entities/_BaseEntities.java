package org.entities;

public abstract class _BaseEntities {
    private int id;

    public _BaseEntities(){}

    public _BaseEntities(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "_BaseEntities{" +
                "id=" + id +
                '}';
    }
}
