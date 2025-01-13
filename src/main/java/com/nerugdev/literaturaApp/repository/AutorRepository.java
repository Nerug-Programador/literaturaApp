package com.nerugdev.literaturaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nerugdev.literaturaApp.model.Autor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByFechaFallecimientoIsNullOrFechaFallecimientoGreaterThan(Integer ano);
    Autor findByNombreAndApellido(String nombre, String apellido);
    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();
    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.libros WHERE (a.fechaFallecimiento IS NULL OR a.fechaFallecimiento > :ano) AND a.fechaNacimiento <= :ano")
    List<Autor> findByFechaFallecimientoIsNullOrFechaFallecimientoGreaterThanAndFechaNacimientoLessThanEqualWithLibros(@Param("ano") Integer ano);
}

