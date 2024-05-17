package com.codigo.builder.controller;

import com.codigo.builder.entidad.Libro;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/patron/builder")
public class LibroController {
    public Libro obtenerLibro(){
        return new Libro.Builder()
                .titulo("Paco Yunque")
                .autor("Cesar vallejo")
                .isbn("XXXX-XXXXX-XXXXX-XXXX")
                .fechaPublicacion(LocalDate.of(1943,4,6))
                .build();
    }
}
