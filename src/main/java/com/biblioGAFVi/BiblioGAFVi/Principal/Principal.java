package com.biblioGAFVi.BiblioGAFVi.Principal;

import com.biblioGAFVi.BiblioGAFVi.Model.*;
import com.biblioGAFVi.BiblioGAFVi.Repository.IAutoresRepository;
import com.biblioGAFVi.BiblioGAFVi.Repository.ILibroRepository;
import com.biblioGAFVi.BiblioGAFVi.Service.ConsumoAPI;
import com.biblioGAFVi.BiblioGAFVi.Service.ConvierteDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private IAutoresRepository repositorioDeAutores;
    private ILibroRepository repositorioDeLibros;

    public Principal(IAutoresRepository repositorioDeAutores, ILibroRepository repositorioDeLibros) {
        this.repositorioDeLibros = repositorioDeLibros;
        this.repositorioDeAutores = repositorioDeAutores;
    }


    public void muestraElMenu() {
        var opcion = -1;
        System.out.println("\n" + "Hola ! Bienvenido a la biblioteca virtual BiblioGAFVi, selecciona con numero alguna de las opciones siguientes: ");
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

            if (!teclado.hasNextInt()) {
                System.out.println("¡Ingresa una opción válida!");
                teclado.nextLine();  // Limpiar el buffer
                continue;
            }

            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosEnUnDeterminadoPeriodo();
                    break;
                case 5:
                    mostrarLibrosEnUnDterminadoIdioma();
                    break;

                case 0:
                    System.out.println("Ha decicido cerrar la aplicación... Gracias por usar BiblioGAFVi");
                    break;
                default:
                    System.out.println("Opción inválida, teclee solamente los numeros de las opciones anteriores");
            }
        }
    }

    // metodo para obtener datos del libro desde la api
    private DatosLibreria getDatosLibreria(String tituloLibro) {
        try {
            // En lugar de usar el metodo replace, se usa este metodo para generar la busqueda en la URL
            String tituloCodificado = URLEncoder.encode(tituloLibro, StandardCharsets.UTF_8.toString());
            // Se crea variable para consumir API y concatenar la URL_BASE con la interaccion del usuario
            var json = consumoAPI.obtenerDatos(URL_BASE + tituloCodificado);
            System.out.println(URL_BASE + tituloCodificado);
            System.out.println(json);

            //Si la respueesta de la API es nula se muestra la respuesta:
            if (json == null || json.isEmpty()) {
                System.out.println( "La API no contiene datos relacionados con esa busqueda.");
                return null;
            } /*else  se imprime el resultado de la consulta  {System.out.println(json);}*/

            // Se des-serializa la respuesta JSON
            DatosLibreria datosLibro = conversor.obtenerDatos(json, DatosLibreria.class);

            // Si no se encuentran resultados...
            if (datosLibro == null || datosLibro.resultados().isEmpty()) {
                System.out.println("No se encontraron resultados para el título: " + tituloLibro);
                return null;
            }
            return datosLibro;
        } catch (Exception e) {
            System.out.println("Error al obtener datos: " + e.getMessage());
            return null;
        }
    }
    private void buscarLibroPorTitulo(){
        System.out.println("Escriba el titulo del libro que desea buscar: ");
        String tituloLibro = teclado.nextLine();

        DatosLibreria datos = getDatosLibreria(tituloLibro); // Obtiene datos de la API

        if (datos != null) {
            System.out.println("Resultados de la busqueda");
            for (DatosLibros datosLibro : datos.resultados()){
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println("Título: " + datosLibro.titulo());
                System.out.println("Autor(es): " + datosLibro.nombreAutor().stream()
                        .map(DatosAutor::nombreAutor).toList());
                System.out.println("Lenguaje(s): " + datosLibro.idioma());
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

                // Verificar si el libro ya existe en la base de datos
                Optional<Libro> libro = repositorioDeLibros.findByTituloIgnoreCase(datosLibro.titulo().toLowerCase());

                if (libro.isPresent()) {
                    System.out.println("El libro ya está en la base de datos.");
                } else {
                    System.out.println("El libro no se encontró en la base de datos.");

                    // Crear un nuevo libro y agregarlo a la base de datos
                    Libro nuevoLibro = new Libro();
                    nuevoLibro.setTitulo(datosLibro.titulo());
                    nuevoLibro.setIdioma(datosLibro.idioma().toString());

                    // Crear o recuperar el autor del libro
                    for (DatosAutor autorDatos : datosLibro.nombreAutor()) {
                        // Verificar si el autor ya existe en la base de datos
                        Autor autor = obtenerAutor(autorDatos);
                        nuevoLibro.setAutor(autor);  // Asociar el autor al libro
                        nuevoLibro.setNombreAutor(autor.getNombreAutor());  // Nombre del autor
                    }

                    // Guardar el libro en la base de datos
                    repositorioDeLibros.save(nuevoLibro);
                    System.out.println("El libro ha sido guardado en la base de datos.");
                }
            }
        }
    }

    private Autor obtenerAutor(DatosAutor datosAutor) {
        // Verificar si el autor ya está en la base de datos
        Autor autorExistente = repositorioDeAutores.findBynombreAutorIgnoreCase(datosAutor.nombreAutor());
        if (autorExistente != null) {
            return autorExistente;  // Devolver el autor existente
        } else {
            // Si no existe, crear un nuevo autor
            Autor nuevoAutor = new Autor(datosAutor);
            return repositorioDeAutores.save(nuevoAutor);  // Guardar el nuevo autor y devolverlo
        }
    }

    private  void  mostrarLibrosBuscados(){
        List<Libro> libro =repositorioDeLibros.findAll();
        if (libro.isEmpty()){
            System.out.println("No hay libros registrados en la base de datos, por favor, primero realice una busqueda.");
            return;
        }
        System.out.println("los libros registrados en la base de datos son : ");
        libro.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }
    private void mostrarAutoresRegistrados() {
        List<Autor> autores = repositorioDeAutores.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
            return;
        }

        System.out.println("Los autores registrados son:\n");

        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombreAutor)) // Ordenar por nombre
                .forEach(autor -> {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*AUTOR-*-*-*-*-*-*-*-*-*-*-*-*");
                    System.out.println("Nombre del autor: " + autor.getNombreAutor());

                    // Obtener los libros asociados a este autor
                    List<Libro> libros = repositorioDeLibros.findByAutor(autor);

                    if (libros.isEmpty()) {
                        System.out.println("Libros asociados a este autor: Ninguno");
                    } else {
                        System.out.println("Libros asociados a este autor: ... " +
                                libros.stream()
                                        .map(Libro::getTitulo)
                                        .toList()); // Mapea a los títulos de los libros
                    }
                    System.out.println("Fecha de nacimiento: " + autor.getNacimiento());
                    System.out.println("Fecha de fallecimiento: " +
                            (autor.getMuerte() != null ? autor.getMuerte() : "El autor"));

                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
                });
    }
    private void  mostrarAutoresVivosEnUnDeterminadoPeriodo(){
        System.out.println("Teclee el año en el que desee revisar que autores estaban vivos ");
        var fecha = teclado.nextInt();
        teclado.nextLine();
        if (fecha < 0){
            System.out.println("Año invalido, ingrese otra fecha");
            return;
        }
        List<Autor>autorPorFecha =
                repositorioDeAutores
                        .findByNacimientoLessThanEqualAndMuerteGreaterThanEqual(fecha,fecha);
        if (autorPorFecha.isEmpty()){
            System.out.println("¡No hay autores registrados con esa fecha!");
            return;
        }
        System.out.println("Los autores vivos  en el año " + fecha + " Son :");
        autorPorFecha.stream()
                .sorted(Comparator.comparing(Autor::getNombreAutor))
                .forEach(System.out::println);
    }

    private  void  mostrarLibrosEnUnDterminadoIdioma(){
        System.out.println("Selecciona las siglas del idioma en el que deseas buscar libros :");
        String menu = """
                es - Epañol
                en - Ingles
                fr - Frances
                pt - Portugues
                """;
        System.out.println(menu);
        var idioma = teclado.nextLine();
        if (!idioma.equals("es") && !idioma.equals("en") && !idioma.equals("fr") && !idioma.equals("pt")) {
            System.out.println("Idioma no válido, intenta de nuevo");
            return;
        }
        List<Libro> libroIdioma = repositorioDeLibros.findByIdiomaContaining(idioma);
        if (libroIdioma.isEmpty()){
            System.out.println("La base de datos no contiene libro con ese idioma");
            return;
        }
        System.out.println(" Estos son los libros que estan en el idioma que selecionaste: \n");
        libroIdioma.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }
}