package com.nerugdev.literaturaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nerugdev.literaturaApp.model.Libro;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);
    List<Libro> findByIdioma(String idioma);
    Libro findByTitulo(String titulo);
}