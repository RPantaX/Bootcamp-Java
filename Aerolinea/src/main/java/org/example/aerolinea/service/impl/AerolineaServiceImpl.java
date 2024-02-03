package org.example.aerolinea.service.impl;

import org.example.aerolinea.entity.Aerolinea;
import org.example.aerolinea.repository.AerolineaRepository;
import org.example.aerolinea.response.ResponseBase;
import org.example.aerolinea.service.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {
    @Autowired
    private AerolineaRepository aerolineaRepository;
    @Override
    public ResponseBase saveAerolinea(Aerolinea aerolinea) {
        return new ResponseBase(HttpStatus.CREATED.value(),"Aerolinea creada", true, Optional.of(aerolineaRepository.save(aerolinea)));
    }

    @Override
    public ResponseBase findById(Integer id) {
        Optional<Aerolinea> aerolineaBd= aerolineaRepository.findById(id);
        if(aerolineaBd.isPresent()){
            return new ResponseBase(HttpStatus.OK.value(), "Aerolinea encontrada", true, Optional.of(aerolineaBd.get()));
        }
        return new ResponseBase(400, "Aerolinea no existe", false, Optional.empty());
    }
}
