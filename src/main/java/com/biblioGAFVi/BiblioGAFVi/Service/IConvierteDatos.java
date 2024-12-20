package com.biblioGAFVi.BiblioGAFVi.Service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
