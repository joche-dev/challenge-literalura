package com.alura.literalura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    private Long numeroDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = (datosLibro.autor() != null && !datosLibro.autor().isEmpty())
                ? new Autor(datosLibro.autor().get(0))
                : null;
        this.idioma = datosLibro.languages().get(0);
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return  "Titulo: " + titulo + "\n" +
                "Autor: " + ((autor != null) ? autor.getNombre() : "desconocido") + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número Descargas: " + ((numeroDescargas != null) ? numeroDescargas : "0");
    }
}

