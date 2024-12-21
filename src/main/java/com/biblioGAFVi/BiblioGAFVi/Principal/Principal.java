package com.biblioGAFVi.BiblioGAFVi.Principal;

import com.biblioGAFVi.BiblioGAFVi.Service.ConsumoAPI;
import com.biblioGAFVi.BiblioGAFVi.Service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    1 - Buscar libro por titulo
                    2 - Mostrar libros ya buscados
                    3 - Mostrar autores registrados
                    4 - Mostrar autores vivos en un determinado año
                    5 - Mostrar libros en algun determinado idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo;
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosEnUnDeterminadoPeriodo;
                    break;
                case 5:
                    mostrarLibrosEnUnDterminadoIdioma;
                    break;

                case 0:
                    System.out.println("Ha decicido cerrar la aplicación... Gracias por usar BiublioGAFVi");
                    break;
                default:
                    System.out.println("Opción inválida, teclee solamente los numeros de las opciones anteriores");
            }
        }

    }
}
