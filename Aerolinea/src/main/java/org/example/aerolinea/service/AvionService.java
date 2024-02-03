package org.example.aerolinea.service;

import org.example.aerolinea.entity.Avion;
import org.example.aerolinea.response.ResponseBase;

import java.util.Optional;

public interface AvionService {
    ResponseBase saveAvion(Avion avion);
    ResponseBase findById(Integer id);
}
