package com.codigo.msregistro.application.middleware;

import com.codigo.msregistro.domain.aggregates.response.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestMiddleware {
    @ExceptionHandler(Exception.class) //engloba todas las excepciones
    public ResponseEntity<ResponseError> errorResponseEntity(Exception exception){
        return  ResponseEntity.status(422).body(new ResponseError(2422, exception.getCause().toString()));
    }

    @ExceptionHandler(NullPointerException.class) //engloba todas las excepciones
    public ResponseEntity<ResponseError> errorResponseEntityNullPinter(Exception exception){
        return  ResponseEntity.status(422).body(new ResponseError(2422, exception.getCause().toString()));
    }

}
