package com.nerugdev.literaturaApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.nerugdev.literaturaApp.service.LibroService;
import com.nerugdev.literaturaApp.service.GutendexAPIService;
import com.nerugdev.literaturaApp.util.StringUtil;
import com.nerugdev.literaturaApp.model.Libro;
import com.nerugdev.literaturaApp.model.LibroDTO;

import java.util.List;

@Controller
public class LibroController {
    @Autowired
    private LibroService libroService;
    @Autowired
    private GutendexAPIService gutendexAPIService;

    public void buscarLibroPorTitulo(String titulo) {
        String tituloNormalizado = StringUtil.normalizar(titulo);
        LibroDTO libroDTO = gutendexAPIService.buscarLibroPorTitulo(tituloNormalizado);

        if (libroDTO != null) {
            if (!libroService.existePorTitulo(libroDTO.getTitulo())) {
                Libro libro = new Libro(libroDTO);
                libroService.guardar(libro);
                mostrarLibro(libro);
            } else {
                System.out.println("El libro ya está registrado en la base de datos.");
            }
        } else {
            System.out.println("No se encontraron resultados para la búsqueda: " + titulo);
        }
    }

    public void listarLibrosRegistrados() {
        List<Libro> libros = libroService.obtenerTodos();
        for (Libro libro : libros) {
            mostrarLibro(libro);
        }
    }

    public void listarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroService.obtenerPorIdioma(idioma);
        for (Libro libro : libros) {
            mostrarLibro(libro);
        }
    }

    private void mostrarLibro(Libro libro) {
        System.out.println("***** LIBRO *****");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor().getApellido() + ", " + libro.getAutor().getNombre());
        System.out.println("Idioma: " + libro.getIdioma());
        System.out.println("Número de descargas: " + libro.getNumeroDescargas());
        System.out.println("******************");
    }
}