package org.example.aerolinea.service;

import org.example.aerolinea.entity.Pasajero;
import org.example.aerolinea.response.ResponseBase;

import java.util.Optional;

public interface PasajeroService {
    ResponseBase savePasajero(Pasajero pasajero);
    ResponseBase findById(Integer id);
}
