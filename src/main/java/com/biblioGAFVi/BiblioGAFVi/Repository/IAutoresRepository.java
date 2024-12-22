package com.biblioGAFVi.BiblioGAFVi.Repository;

import com.biblioGAFVi.BiblioGAFVi.Model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAutoresRepository extends JpaRepository<Autor,Long> {
    Autor findBynombreAutorIgnoreCase(String nombreAutor);
    List<Autor> findByFechaDeNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(int anoInicial, int anoFinal);
}
