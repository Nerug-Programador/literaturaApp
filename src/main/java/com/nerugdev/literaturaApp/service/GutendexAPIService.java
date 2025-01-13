package com.nerugdev.literaturaApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.nerugdev.literaturaApp.model.LibroDTO;
import com.nerugdev.literaturaApp.model.AutorDTO;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class GutendexAPIService {
    @Autowired
    private RestTemplate restTemplate;

    public LibroDTO buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books?search=" + titulo;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = response.getBody();
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray results = jsonObject.getJSONArray("results");

            if (results.length() > 0) {
                JSONObject firstResult = results.getJSONObject(0);

                String title = firstResult.getString("title");
                String idioma = firstResult.getJSONArray("languages").getString(0);
                int numeroDescargas = firstResult.getInt("download_count");

                JSONObject author = firstResult.getJSONArray("authors").getJSONObject(0);
                AutorDTO autorDTO = new AutorDTO();
                autorDTO.setNombre(author.getString("name").split(", ")[1]);
                autorDTO.setApellido(author.getString("name").split(", ")[0]);
                autorDTO.setFechaNacimiento(author.optInt("birth_year", 0));
                autorDTO.setFechaFallecimiento(author.optInt("death_year", 0));

                LibroDTO libroDTO = new LibroDTO();
                libroDTO.setTitulo(title);
                libroDTO.setIdioma(idioma);
                libroDTO.setNumeroDescargas(numeroDescargas);
                libroDTO.setAutor(autorDTO);

                return libroDTO;
            } else {
                return null;
            }
        } else {
            System.out.println("No se encontraron resultados para la búsqueda: " + titulo);
            return null;
        }
    }
}