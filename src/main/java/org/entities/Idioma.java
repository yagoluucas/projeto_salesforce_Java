package org.entities;

public class Idioma extends _BaseEntities {
    private String idioma;

    public Idioma(){}

    public Idioma(int id, String idioma) {
        super(id);
        this.idioma = idioma;
    }

    public Idioma (String idioma) {
        this.idioma = idioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Idioma{" +
                "idioma='" + idioma + '\'' +
                "} " + super.toString();
    }
}
