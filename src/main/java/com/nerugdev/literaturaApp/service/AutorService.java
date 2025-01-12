package com.nerugdev.literaturaApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nerugdev.literaturaApp.repository.AutorRepository;
import com.nerugdev.literaturaApp.model.Autor;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    public List<Autor> obtenerAutoresVivosEnAno(int ano) {
        return autorRepository.findByFechaFallecimientoIsNullOrFechaFallecimientoGreaterThan(ano);
    }
}