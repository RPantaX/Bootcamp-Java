package org.example.aerolinea.service.impl;

import org.example.aerolinea.entity.Avion;
import org.example.aerolinea.entity.Pasajero;
import org.example.aerolinea.repository.PasajeroRepository;
import org.example.aerolinea.response.ResponseBase;
import org.example.aerolinea.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasajeroServiceImpl implements PasajeroService {
    @Autowired
    PasajeroRepository pasajeroRepository;
    @Override
    public ResponseBase savePasajero(Pasajero pasajero) {
        return new ResponseBase(HttpStatus.CREATED.value(),"Pasajero creado", true, Optional.of(pasajeroRepository.save(pasajero)));
    }

    @Override
    public ResponseBase findById(Integer id) {
        Optional<Pasajero> pasajeroBd= pasajeroRepository.findById(id);
        if(pasajeroBd.isPresent()){
            return new ResponseBase(HttpStatus.OK.value(), "Pasajero encontrado", true, Optional.of(pasajeroBd.get()));
        }
        return new ResponseBase(400, "Pasajero no existe", false, Optional.empty());
    }
}
