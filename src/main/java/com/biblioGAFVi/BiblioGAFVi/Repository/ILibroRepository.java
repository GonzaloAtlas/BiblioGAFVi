package com.biblioGAFVi.BiblioGAFVi.Repository;

import com.biblioGAFVi.BiblioGAFVi.Model.Autor;
import com.biblioGAFVi.BiblioGAFVi.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloIgnoreCase(String titulo);
    List<Libro> findByIdiomaContaining(String idiomas);
    List<Libro> findByAutor(Autor autor);
}
