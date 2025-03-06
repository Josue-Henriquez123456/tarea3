/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bibliotecavirtual;

/**
 *
 * 
 */
import java.io.*;
public class Libro implements Serializable {
   private static final long serialVersionUID = 1L;
    private final String titulo;
    private final String autor;
    private final int anioPublicacion;
    private final String genero;
    private boolean disponible;
    private double calificacion;
    private String comentarios;
    private int numCalificaciones;
    
    public Libro(String titulo, String autor, int anioPublicacion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.disponible = true;
        this.calificacion = 0.0;
        this.comentarios = "";
        this.numCalificaciones = 0;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void agregarComentario(String comentario) {
        this.comentarios += "- " + comentario + "\n";
    }
    
    public void calificar(double nuevaCalificacion) {
        if (nuevaCalificacion >= 0 && nuevaCalificacion <= 5) {
            this.calificacion = ((this.calificacion * numCalificaciones) + nuevaCalificacion) / (numCalificaciones + 1);
            this.numCalificaciones++;
        }
    }
    
    @Override
    public String toString() {
        return "Titulo: " + titulo + 
               "\nAutor: " + autor + 
               "\nPublicado en: " + anioPublicacion + 
               "\nGenero: " + genero + 
               "\nDisponible: " + (disponible ? "Si" : "No") +
               "\nCalificacion promedio: " + String.format("%.2f", calificacion) + " (" + numCalificaciones + " calificaciones)" +
               "\nComentarios: \n" + comentarios;
    }
}

