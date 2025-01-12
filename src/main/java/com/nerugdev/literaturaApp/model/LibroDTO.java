package com.nerugdev.literaturaApp.model;

public class LibroDTO {
    private String titulo;
    private String idioma;
    private int numeroDescargas;
    private AutorDTO autor;

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }
}