package com.nerugdev.literaturaApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nerugdev.literaturaApp.repository.LibroRepository;
import com.nerugdev.literaturaApp.model.Libro;

import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public List<Libro> obtenerPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    public boolean existePorTitulo(String titulo) {
        return libroRepository.existsByTitulo(titulo);
    }

    public void guardar(Libro libro) {
        libroRepository.save(libro);
    }
}