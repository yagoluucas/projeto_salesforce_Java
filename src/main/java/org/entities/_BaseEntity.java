package org.entities;

public abstract class _BaseEntity {
    private int id;

    public _BaseEntity(){}

    public _BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // os metodos isEmailValid e isPhoneValid serve para validar o email e o telefone vindo do front-end
    public boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
    public boolean isPhoneValid(String phone) {
        return phone.replaceAll("[/-]", "").matches("[0-9]{11}");
    }

    @Override
    public String toString() {
        return "_BaseEntities{" +
                "id=" + id +
                '}';
    }
}
