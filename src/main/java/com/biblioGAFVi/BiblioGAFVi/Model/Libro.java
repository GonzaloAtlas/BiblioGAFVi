package com.biblioGAFVi.BiblioGAFVi.Model;

public class Libro {
    private Long id;
    private String titulo;
    private String nombreAutor;
    private String lenguaje;
    private Double descargas;

    private Autor autor;

    public Libro(DatosLibros datosLibros, Autor autor) {
        this.titulo =  datosLibros.titulo();
        setLenguaje(datosLibros.idioma().toString());
        this.nombreAutor = autor.getNombreAutor() ;
        this.descargas = datosLibros.descargas();
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "-*-*-*-*-*-*-*-* DATOS DEL LIBRO -*-*-*-*-*-*-*-* " + "\n" +
                "Titulo = " + titulo + "\n" +
                "Autor = " + nombreAutor + "\n" +
                "Idioma = " + lenguaje + "\n" +
                "Numero de descargas = " + descargas + "\n" +
                "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* " + "\n";
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getNombreAutor() {return nombreAutor;}

    public void setNombreAutor(String autor) {this.nombreAutor = autor;}

    public String getLenguaje() {return lenguaje;}

    public void setLenguaje(String lenguaje) {this.lenguaje = lenguaje;}

    public Double getDescargas() {return descargas;}

    public void setDescargas(Double descargas) {this.descargas = descargas;}

}
