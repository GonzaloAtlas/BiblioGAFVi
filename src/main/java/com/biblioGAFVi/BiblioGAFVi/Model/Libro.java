package com.biblioGAFVi.BiblioGAFVi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @Column(name = "nombre_autor")
    private String nombreAutor;

    @Column(name = "idioma")
    private String idioma;
    private Double descargas;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Libro(){}

    public Libro(DatosLibros datosLibros, Autor autor) {
        this.titulo =  datosLibros.titulo();
        setIdioma(datosLibros.idioma().toString());
        this.nombreAutor = autor.getNombreAutor() ;
        this.descargas = datosLibros.descargas();
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "-*-*-*-*-*-*-*-* DATOS DEL LIBRO -*-*-*-*-*-*-*-* " + "\n" +
                "Titulo = " + titulo + "\n" +
                "Autor = " + nombreAutor + "\n" +
                "Idioma = " + idioma + "\n" +
                "Numero de descargas = " + descargas + "\n" +
                "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* " + "\n";
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getNombreAutor() {return nombreAutor;}

    public void setNombreAutor(String autor) {this.nombreAutor = autor;}

    public String getIdioma() {return idioma;}

    public void setIdioma(String idioma) {this.idioma = idioma;}

    public Double getDescargas() {return descargas;}

    public void setDescargas(Double descargas) {this.descargas = descargas;}

}
