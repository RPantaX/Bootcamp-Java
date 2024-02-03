package org.example.aerolinea.service.impl;

import org.example.aerolinea.entity.Reserva;
import org.example.aerolinea.entity.Vuelo;
import org.example.aerolinea.repository.VueloRepository;
import org.example.aerolinea.response.ResponseBase;
import org.example.aerolinea.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {
    @Autowired
    VueloRepository vueloRepository;
    @Override
    public ResponseBase saveVuelo(Vuelo vuelo) {
        return new ResponseBase(HttpStatus.CREATED.value(),"Vuelo creado", true, Optional.of(vueloRepository.save(vuelo)));

    }

    @Override
    public ResponseBase findById(Integer id) {
        Optional<Vuelo> vueloBd= vueloRepository.findById(id);
        if(vueloBd.isPresent()){
            return new ResponseBase(HttpStatus.OK.value(), "Vuelo encontrado", true, Optional.of(vueloBd.get()));
        }
        return new ResponseBase(400, "Vuelo no existe", false, Optional.empty());
    }
}
