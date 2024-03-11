package com.codigo.msregistro.domain.aggregates.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestPersona {
    private String tipoDoc;
    private String numDoc;
}
