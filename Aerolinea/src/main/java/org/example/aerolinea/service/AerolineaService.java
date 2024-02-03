package org.example.aerolinea.service;

import org.example.aerolinea.entity.Aerolinea;
import org.example.aerolinea.response.ResponseBase;

import java.util.Optional;

public interface AerolineaService {
    ResponseBase saveAerolinea(Aerolinea aerolinea);
    ResponseBase findById(Integer id);
}
