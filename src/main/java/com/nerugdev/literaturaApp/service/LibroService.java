package com.nerugdev.literaturaApp.service;

import com.nerugdev.literaturaApp.repository.LibroRepository;
import com.nerugdev.literaturaApp.model.Libro;
import com.nerugdev.literaturaApp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private GutendexAPIService gutendexAPIService;

    public String buscarLibroPorTitulo(String titulo) {
        String normalizedTitulo = StringUtil.normalize(titulo);
        Libro libro = gutendexAPIService.buscarLibro(normalizedTitulo);
        if (libro == null) {
            return "No se encontraron resultados para la búsqueda.";
        }
        libroRepository.save(libro);
        return libro.toString();
    }

    public List<String> listarLibros() {
        return libroRepository.findAll().stream().map(Libro::toString).collect(Collectors.toList());
    }

    public List<String> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma).stream().map(Libro::toString).collect(Collectors.toList());
    }
}