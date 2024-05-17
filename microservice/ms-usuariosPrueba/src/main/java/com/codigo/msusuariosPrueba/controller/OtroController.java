package com.codigo.msusuariosPrueba.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/ms-prueba/v1/otro")
@RefreshScope
public class OtroController {
    @Value("${valor.propiedad.otro}") //aqui obtiene el nuevo valor que está en las propiedades
    private String valorProp;

    @GetMapping("")
    public ResponseEntity<String> getValor(){return ResponseEntity.ok(valorProp);}
}
