package com.alura.literalura.principal;

import com.alura.literalura.models.Autor;
import com.alura.literalura.models.Datos;
import com.alura.literalura.models.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final static String URL_BASE = "https://gutendex.com/books/?search=";
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;


    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu () {
        var opcion = -1;
        var menu = """
                    -----------------
                    📚 APLICACIÓN LITERALURA
                  
                    1 - Buscar libros por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Top 10 libros más descargados
                    0 - Salir
                    """;

        while (opcion != 0) {
            try{
                System.out.println("\n" + menu);
                System.out.print("Elije una opción del menu a través de su número: ");
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("🟡 Opción no válida, debes ingresar un número del menu.");
                teclado.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    top10Libros();
                    break;

                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;

                default:
                    System.out.println("🟡 Opción no válida, intenta de nuevo.");
            }

        }
    }


    private Datos consultarDatosLibros() {
        System.out.print("🔎 Escribe el nombre del libro a buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        return conversor.obtenerDatos(json, Datos.class);
    }

    private void buscarLibrosPorTitulo() {
        Datos datos = consultarDatosLibros();
        if (datos.resultados().isEmpty()){
            System.out.println("🔵 No se encuentra el libro en Gutendex.com.");
            return;
        }

        Libro libro = new Libro(datos.resultados().get(0));

        Optional<Libro> libroBuscado = libroRepository.findByTituloContainsIgnoreCase(libro.getTitulo());
        if (libroBuscado.isPresent()) {
            System.out.println("🔵 El libro '" + libro.getTitulo() + "' ya se encuentra registrado.");
            return;
        }

        if (libro.getAutor() != null && libro.getAutor().getNombre() != null) {
            Optional<Autor> autorBuscado = autorRepository.findByNombreContainsIgnoreCase(libro.getAutor().getNombre());
            if (autorBuscado.isPresent()) {
                libro.setAutor(autorBuscado.get());
            } else {
                autorRepository.save(libro.getAutor());
                libro.setAutor(libro.getAutor());
            }
        }

        libroRepository.save(libro);
        System.out.println("\n🟢 Libro registrado exitosamente:\n--------------------");
        System.out.println(libro);
    }

    private void listarLibrosRegistrados() {
        List<Libro> librosRegistrados = libroRepository.findAll();
        if (librosRegistrados.isEmpty()) {
            System.out.println("🔵 No hay libros registrados en literAlura.");
            return;
        }
        System.out.println("\n📚 Lista de libros registrados:");
        librosRegistrados.forEach(libro -> {
            System.out.println("--------------------");
            System.out.println(libro);
        });
    }

    private void listarAutoresRegistrados() {
        List<Autor> autoresRegistrados = autorRepository.findAll();
        if (autoresRegistrados.isEmpty()){
            System.out.println("🔵 No hay autores registrados en literAlura.");
            return;
        }
        System.out.println("\n📚 Lista de autores registrados:");
        autoresRegistrados.forEach(autor -> {
            System.out.println("--------------------");
            System.out.println(autor);
        });
    }

    private void listarAutoresVivosPorAnio(){
        try{
            System.out.print("🔎 Ingresa un año para listar autores vivos, (Ej: 2000): ");
            var yearQuery = teclado.nextInt();
            teclado.nextLine();
            if(yearQuery < 0) {
                System.out.println("🟡 El año ingresado no puede ser negativo.");
                return;
            }
            List<Autor> autoresVivos = autorRepository.findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(yearQuery,yearQuery);
            if (autoresVivos.isEmpty()) {
                System.out.println("🔵 No hay autores vivos registrados en el año " + yearQuery + ".");
                return;
            }
            System.out.println("\n📚 Lista de autores vivos registrados en el año " + yearQuery + ":");
            autoresVivos.forEach(autor -> {
                System.out.println("--------------------");
                System.out.println(autor);
            });

        } catch (InputMismatchException e) {
            System.out.println("🟡 El año ingresado no es valido.");
            teclado.nextLine();
        }
    }

    private void listarLibrosPorIdioma(){
        System.out.print("""
                
                --------------------
                Idiomas disponibles para buscar:
                es - Español
                en - Inglés
                fr - Francés
                pt - Portugués
                """);
        System.out.print("Escribe el idioma por el que deseas buscar, (Ej: en): ");
        var idioma = teclado.nextLine();
        if (!idioma.equals("es") && !idioma.equals("en") && !idioma.equals("fr") && !idioma.equals("pt")) {
            System.out.println("🟡 Idioma no válido, intenta de nuevo.");
            return;
        }

        List<Libro> librosPorIdioma = libroRepository.findByIdiomaContaining(idioma);
        if (librosPorIdioma.isEmpty()){
            System.out.println("🔵 No hay libros registrados en ese idioma");
            return;
        }
        System.out.println("\n📚 Lista de libros por el idioma " + idioma + ":");
        librosPorIdioma.forEach(libro -> {
            System.out.println("--------------------");
            System.out.println(libro);
        });
    }

    private void top10Libros(){
        List<Libro> librosTop10 = libroRepository.findTop10ByOrderByNumeroDescargasDesc();
        AtomicInteger index = new AtomicInteger(1);
        if (librosTop10.isEmpty()){
            System.out.println("🔵 No hay libros registrados en literAlura.");
            return;
        }
        System.out.println("\n📚 Top 10 libros más descargados:");
        librosTop10.forEach(libro -> {
            System.out.print(String.format("""
                    --------------------
                    %d - Titulo: %s
                        Número descargas: %d
                    """, index.getAndIncrement(), libro.getTitulo(), libro.getNumeroDescargas()
            ));
        });
    }

}
