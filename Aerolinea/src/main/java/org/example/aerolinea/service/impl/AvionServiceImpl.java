package org.example.aerolinea.service.impl;

import org.example.aerolinea.entity.Aerolinea;
import org.example.aerolinea.entity.Avion;
import org.example.aerolinea.repository.AvionRepository;
import org.example.aerolinea.response.ResponseBase;
import org.example.aerolinea.service.AvionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvionServiceImpl implements AvionService {
    @Autowired
    AvionRepository avionRepository;
    @Override
    public ResponseBase saveAvion(Avion avion) {
        return new ResponseBase(HttpStatus.CREATED.value(),"Avión creado", true, Optional.of(avionRepository.save(avion)));
    }

    @Override
    public ResponseBase findById(Integer id) {
        Optional<Avion> avionBd= avionRepository.findById(id);
        if(avionBd.isPresent()){
            return new ResponseBase(HttpStatus.OK.value(), "Avión encontrado", true, Optional.of(avionBd.get()));
        }
        return new ResponseBase(400, "Avión no existe", false, Optional.empty());
    }
}
