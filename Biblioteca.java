/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bibliotecavirtual;

/**
 *
 * 
 */
// Clase Biblioteca
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 */
public class Biblioteca implements Serializable {
   private List<Libro> libros;
    private final Map<String, Integer> estadisticasGenero;
    private static final String ARCHIVO_LIBROS = "libros.dat";
    
    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.estadisticasGenero = new HashMap<>();
        cargarLibros();
    }
    
    public void guardarLibros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_LIBROS))) {
            oos.writeObject(libros);
            System.out.println("Datos guardados exitosamente");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private void cargarLibros() {
        File archivo = new File(ARCHIVO_LIBROS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(ARCHIVO_LIBROS))) {
                libros = (List<Libro>) ois.readObject();
                for (Libro libro : libros) {
                    estadisticasGenero.put(libro.getGenero(), 
                        estadisticasGenero.getOrDefault(libro.getGenero(), 0) + 1);
                }
                System.out.println("Datos cargados exitosamente");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar los datos: " + e.getMessage());
                libros = new ArrayList<>();
            }
        }
    }
    
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        estadisticasGenero.put(libro.getGenero(), 
            estadisticasGenero.getOrDefault(libro.getGenero(), 0) + 1);
        guardarLibros();
    }
    
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }
    
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }
    
    public boolean prestarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) && libro.isDisponible()) {
                libro.setDisponible(false);
                guardarLibros();
                return true;
            }
        }
        return false;
    }
    
    public boolean devolverLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) && !libro.isDisponible()) {
                libro.setDisponible(true);
                guardarLibros();
                return true;
            }
        }
        return false;
    }
    
    public void mostrarLibrosDisponibles() {
        System.out.println("\n=== Libros Disponibles ===");
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                System.out.println("\n" + libro);
            }
        }
    }
    
    public void mostrarEstadisticasGenero() {
        System.out.println("\n=== Estadisticas por Genero ===");
        for (Map.Entry<String, Integer> entry : estadisticasGenero.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " libros");
        }
    }
    
    public boolean calificarLibro(String titulo, double calificacion) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libro.calificar(calificacion);
                guardarLibros();
                return true;
            }
        }
        return false;
    }
    
    public boolean agregarComentario(String titulo, String comentario) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libro.agregarComentario(comentario);
                guardarLibros();
                return true;
            }
        }
        return false;
    }
}

