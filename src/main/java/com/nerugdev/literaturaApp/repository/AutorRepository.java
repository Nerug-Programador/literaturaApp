package com.nerugdev.literaturaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nerugdev.literaturaApp.model.Autor;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByFechaFallecimientoIsNullOrFechaFallecimientoGreaterThan(Integer ano);
}