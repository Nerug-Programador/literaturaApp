package com.nerugdev.literaturaApp.controller;

import com.nerugdev.literaturaApp.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/buscar")
    public String buscarLibroPorTitulo(@RequestParam String titulo) {
        return libroService.buscarLibroPorTitulo(titulo);
    }

    @GetMapping("/listar")
    public List<String> listarLibros() {
        return libroService.listarLibros();
    }

    @GetMapping("/idioma")
    public List<String> listarLibrosPorIdioma(@RequestParam String idioma) {
        return libroService.listarLibrosPorIdioma(idioma);
    }
}