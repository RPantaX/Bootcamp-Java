package com.codigo.msregistro.infraestructure.util;

import com.codigo.msregistro.domain.aggregates.dto.PersonaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component //estos métodos son de apollo, pot eso es component
public class Util {
    //método de converción
    public static String convertToJson(PersonaDTO personaDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(personaDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertFromJson(String json, Class<T> valueType){
        ObjectMapper objectMapper =  new ObjectMapper();
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
