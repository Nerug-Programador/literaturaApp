package com.nerugdev.literaturaApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.nerugdev.literaturaApp.model.LibroDTO;

@Service
public class GutendexAPIService {
    @Autowired
    private RestTemplate restTemplate;

    public LibroDTO buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books?search=" + titulo;
        // Lógica para llamar a la API y mapear el resultado a LibroDTO
        return null; // Devuelve el objeto LibroDTO mapeado
    }
}