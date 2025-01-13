package com.nerugdev.literaturaApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.nerugdev.literaturaApp.service.LibroService;
import com.nerugdev.literaturaApp.service.GutendexAPIService;
import com.nerugdev.literaturaApp.service.AutorService;
import com.nerugdev.literaturaApp.util.StringUtil;
import com.nerugdev.literaturaApp.model.Autor;
import com.nerugdev.literaturaApp.model.AutorDTO;
import com.nerugdev.literaturaApp.model.Libro;
import com.nerugdev.literaturaApp.model.LibroDTO;

import java.util.List;

@Controller
public class LibroController {
    @Autowired
    private LibroService libroService;
    @Autowired
    private GutendexAPIService gutendexAPIService;
    @Autowired
    private AutorService autorService;

    public void buscarLibroPorTitulo(String titulo) {
        String tituloNormalizado = StringUtil.normalizar(titulo);
        LibroDTO libroDTO = gutendexAPIService.buscarLibroPorTitulo(tituloNormalizado);

        if (libroDTO != null) {
            if (!libroService.existePorTitulo(libroDTO.getTitulo())) {
                AutorDTO autorDTO = libroDTO.getAutor();
                Autor autor = autorService.obtenerPorNombreYApellido(autorDTO.getNombre(), autorDTO.getApellido());
                if (autor == null) {
                    autor = new Autor();
                    autor.setNombre(autorDTO.getNombre());
                    autor.setApellido(autorDTO.getApellido());
                    autor.setFechaNacimiento(autorDTO.getFechaNacimiento());
                    autor.setFechaFallecimiento(autorDTO.getFechaFallecimiento());
                    autorService.guardar(autor);
                }
                Libro libro = new Libro(libroDTO, autor);
                libroService.guardar(libro);
                mostrarLibro(libro);
            } else {
                Libro libroExistente = libroService.obtenerPorTitulo(libroDTO.getTitulo());
                if (libroExistente.getAutor() == null) {
                    AutorDTO autorDTO = libroDTO.getAutor();
                    Autor autor = autorService.obtenerPorNombreYApellido(autorDTO.getNombre(), autorDTO.getApellido());
                    if (autor == null) {
                        autor = new Autor();
                        autor.setNombre(autorDTO.getNombre());
                        autor.setApellido(autorDTO.getApellido());
                        autor.setFechaNacimiento(autorDTO.getFechaNacimiento());
                        autor.setFechaFallecimiento(autorDTO.getFechaFallecimiento());
                        autorService.guardar(autor);
                    }
                    libroExistente.setAutor(autor);
                    libroService.guardar(libroExistente);
                }
                mostrarLibro(libroExistente);
            }
        } else {
            System.out.println("No se encontraron resultados para la búsqueda: " + titulo);
        }
    }

    private void mostrarLibro(Libro libro) {
        System.out.println("***** LIBRO *****");
        System.out.println("Título: " + libro.getTitulo());
        if (libro.getAutor() != null) {
            System.out.println("Autor: " + libro.getAutor().getApellido() + ", " + libro.getAutor().getNombre());
        } else {
            System.out.println("Autor: Desconocido");
        }
        System.out.println("Idioma: " + libro.getIdioma());
        System.out.println("Número de descargas: " + libro.getNumeroDescargas());
        System.out.println("******************");
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
}