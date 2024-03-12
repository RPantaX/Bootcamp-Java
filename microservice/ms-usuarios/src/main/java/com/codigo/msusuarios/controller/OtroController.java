package com.codigo.msusuarios.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ms-usuario/v1/otro")
@RequiredArgsConstructor
@RefreshScope //debe ser actualizado dinamicamente cuando se actualiza los properties (las propiedades de la configuración)
public class OtroController {

    @Value("${valor.propiedad}") //aqui obtiene el nuevo valor que está en las propiedades
    private String valorProp;

    @GetMapping("")
    public ResponseEntity<String> getValor(){
        return ResponseEntity.ok(valorProp);
    }
}
