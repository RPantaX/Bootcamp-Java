package org.example.aerolinea.service;

import org.example.aerolinea.entity.Vuelo;
import org.example.aerolinea.response.ResponseBase;

import java.util.Optional;

public interface VueloService {
    ResponseBase saveVuelo(Vuelo vuelo);
    ResponseBase findById(Integer id);
}
