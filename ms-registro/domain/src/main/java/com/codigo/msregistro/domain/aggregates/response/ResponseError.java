package com.codigo.msregistro.domain.aggregates.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseError {
    private Integer code;
    private String message;
}
