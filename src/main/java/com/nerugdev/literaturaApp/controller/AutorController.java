package com.nerugdev.literaturaApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.nerugdev.literaturaApp.service.AutorService;
import com.nerugdev.literaturaApp.model.Autor;
import com.nerugdev.literaturaApp.model.Libro;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AutorController {
    @Autowired
    private AutorService autorService;

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorService.obtenerTodos();
        for (Autor autor : autores) {
            mostrarAutor(autor);
        }
    }

    public void listarAutoresVivosEnAno(int ano) {
        List<Autor> autores = autorService.obtenerAutoresVivosEnAno(ano);
        for (Autor autor : autores) {
            mostrarAutor(autor);
        }
    }

    private void mostrarAutor(Autor autor) {
        System.out.println("Autor: " + autor.getApellido() + ", " + autor.getNombre());
        System.out.println("Fecha de nacimiento: " + autor.getFechaNacimiento());
        System.out.println("Fecha de fallecimiento: " + (autor.getFechaFallecimiento() != null ? autor.getFechaFallecimiento() : "Vivo"));
        System.out.println("Libros: " + autor.getLibros().stream().map(Libro::getTitulo).collect(Collectors.joining(", ")));
        System.out.println("************************************************************");
    }
}