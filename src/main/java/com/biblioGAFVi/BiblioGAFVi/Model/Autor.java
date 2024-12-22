package com.biblioGAFVi.BiblioGAFVi.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="autor")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String nombreAutor;
    private  int nacimiento;
    private  int muerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    private List<Libro> libro = new ArrayList<>();

    public Autor(DatosAutor datosAutor) {
        this.nombreAutor = datosAutor.nombreAutor();
        this.nacimiento = datosAutor.nacimiento()!= null ? datosAutor.nacimiento() : 0;
        this.muerte = datosAutor.muerte()!= null ? datosAutor.muerte() : 0;
    }

    @Override
    public String toString() {
        String fechaNacimientoStr = (nacimiento != 0) ? String.valueOf(nacimiento) : "Fecha no disponible";
        String fechaFallecimientoStr = (muerte != 0) ? String.valueOf(muerte) : "Aún vive";


        return  "-*-*-*-*-*-*-*-* DATOS DEL AUTOR/AUTORES -*-*-*-*-*-*-*-*" +
                "Nombre del autor =" + nombreAutor + "\n"  +
                "Fecha de nacimiento = " + fechaNacimientoStr + "\n" +
                "Fecha de fallecimiento = " + fechaFallecimientoStr + "\n" +
                "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*" + "\n";
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNombreAutor() {return nombreAutor;}

    public void setNombreAutor(String nombreAutor) {this.nombreAutor = nombreAutor;}

    public Integer getNacimiento() {return nacimiento;}

    public void setNacimiento(int nacimiento) {this.nacimiento = nacimiento;}

    public Integer getMuerte() {return muerte;}

    public void setMuerte(int muerte) {this.muerte = muerte;}

    public List<Libro> getLibro() {return libro;}

    public void setLibro(List<Libro> libro) {this.libro = libro;}
}
