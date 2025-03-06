/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bibliotecavirtual;

/**
 *
 * 
 */
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Bibliotecavirtual implements Serializable {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Biblioteca biblioteca = new Biblioteca();
            
            int opcion;
            do {
                System.out.println("\n=== BIBLIOTECA VIRTUAL ===");
                System.out.println("1. Agregar libro");
                System.out.println("2. Buscar por titulo");
                System.out.println("3. Buscar por autor");
                System.out.println("4. Prestar libro");
                System.out.println("5. Devolver libro");
                System.out.println("6. Mostrar libros disponibles");
                System.out.println("7. Mostrar estadisticas por genero");
                System.out.println("8. Calificar libro");
                System.out.println("9. Agregar comentario a libro");
                System.out.println("10. Salir");
                System.out.print("Seleccione una opcion: ");
                
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Titulo: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        System.out.print("Año de publicacion: ");
                        int anio = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Genero: ");
                        String genero = scanner.nextLine();
                        
                        biblioteca.agregarLibro(new Libro(titulo, autor, anio, genero));
                        System.out.println("Libro agregado exitosamente");
                    }
                    
                    case 2 -> {
                        System.out.print("Ingrese el titulo a buscar: ");
                        String busquedaTitulo = scanner.nextLine();
                        List<Libro> resultadosTitulo = biblioteca.buscarPorTitulo(busquedaTitulo);
                        if (resultadosTitulo.isEmpty()) {
                            System.out.println("No se encontraron libros con ese titulo");
                        } else {
                            for (Libro libro : resultadosTitulo) {
                                System.out.println("\n" + libro);
                            }
                        }
                    }
                    
                    case 3 -> {
                        System.out.print("Ingrese el autor a buscar: ");
                        String busquedaAutor = scanner.nextLine();
                        List<Libro> resultadosAutor = biblioteca.buscarPorAutor(busquedaAutor);
                        if (resultadosAutor.isEmpty()) {
                            System.out.println("No se encontraron libros de ese autor");
                        } else {
                            for (Libro libro : resultadosAutor) {
                                System.out.println("\n" + libro);
                            }
                        }
                    }
                    
                    case 4 -> {
                        System.out.print("Ingrese el titulo del libro a prestar: ");
                        String prestamo = scanner.nextLine();
                        if (biblioteca.prestarLibro(prestamo)) {
                            System.out.println("Libro prestado exitosamente");
                        } else {
                            System.out.println("El libro no esta disponible o no existe");
                        }
                    }
                    
                    case 5 -> {
                        System.out.print("Ingrese el titulo del libro a devolver: ");
                        String devolucion = scanner.nextLine();
                        if (biblioteca.devolverLibro(devolucion)) {
                            System.out.println("Libro devuelto exitosamente");
                        } else {
                            System.out.println("El libro no esta prestado o no existe");
                        }
                    }
                    
                    case 6 -> biblioteca.mostrarLibrosDisponibles();
                    
                    case 7 -> biblioteca.mostrarEstadisticasGenero();
                    
                    case 8 -> {
                        System.out.print("Ingrese el titulo del libro a calificar: ");
                        String libroCalificar = scanner.nextLine();
                        System.out.print("Ingrese la calificacion (0-5): ");
                        double calificacion = scanner.nextDouble();
                        if (biblioteca.calificarLibro(libroCalificar, calificacion)) {
                            System.out.println("Calificacion agregada exitosamente");
                        } else {
                            System.out.println("Libro no encontrado");
                        }
                    }
                    
                    case 9 -> {
                        System.out.print("Ingrese el titulo del libro a comentar: ");
                        String libroComentar = scanner.nextLine();
                        System.out.print("Ingrese su comentario: ");
                        String comentario = scanner.nextLine();
                        if (biblioteca.agregarComentario(libroComentar, comentario)) {
                            System.out.println("Comentario agregado exitosamente");
                        } else {
                            System.out.println("Libro no encontrado");
                        }
                    }
                    
                    case 10 -> {
                        biblioteca.guardarLibros();
                        System.out.println("Gracias por usar la biblioteca virtual!");
                    }
                    
                    default -> System.out.println("Opcion no valida");
                }
                
            } while (opcion != 10);
        }
    }
}