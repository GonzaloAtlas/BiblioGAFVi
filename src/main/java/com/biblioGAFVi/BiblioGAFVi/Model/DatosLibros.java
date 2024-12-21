package com.biblioGAFVi.BiblioGAFVi.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros (
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")String autor,
        @JsonAlias("languages")String lenguaje,
        @JsonAlias("subjects") String genero,
        @JsonAlias("download_count") Integer descargas
){
}
