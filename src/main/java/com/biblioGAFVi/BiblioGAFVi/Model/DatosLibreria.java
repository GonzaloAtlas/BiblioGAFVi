package com.biblioGAFVi.BiblioGAFVi.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibreria (
        @JsonAlias("results")List<DatosLibros> resultados) {
}
