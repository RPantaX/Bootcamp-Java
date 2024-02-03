package org.example.aerolinea.service.impl;

import org.example.aerolinea.entity.Pasajero;
import org.example.aerolinea.entity.Piloto;
import org.example.aerolinea.repository.PilotoRepository;
import org.example.aerolinea.response.ResponseBase;
import org.example.aerolinea.service.PilotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PilotoServiceImpl implements PilotoService {
    @Autowired
    PilotoRepository pilotoRepository;
    @Override
    public ResponseBase savePiloto(Piloto piloto) {
        return new ResponseBase(HttpStatus.CREATED.value(),"Piloto creado", true, Optional.of(pilotoRepository.save(piloto)));
    }

    @Override
    public ResponseBase findById(Integer id) {
        Optional<Piloto> pilotoBd= pilotoRepository.findById(id);
        if(pilotoBd.isPresent()){
            return new ResponseBase(HttpStatus.OK.value(), "Piloto encontrado", true, Optional.of(pilotoBd.get()));
        }
        return new ResponseBase(400, "Piloto no existe", false, Optional.empty());
    }
}
