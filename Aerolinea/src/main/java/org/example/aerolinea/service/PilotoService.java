package org.example.aerolinea.service;

import org.example.aerolinea.entity.Piloto;
import org.example.aerolinea.response.ResponseBase;

import java.util.Optional;

public interface PilotoService {
    ResponseBase savePiloto(Piloto piloto);
    ResponseBase findById(Integer id);
}
