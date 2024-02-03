package org.example.aerolinea.service.impl;

import org.example.aerolinea.entity.Piloto;
import org.example.aerolinea.entity.Reserva;
import org.example.aerolinea.repository.ReservaRepository;
import org.example.aerolinea.response.ResponseBase;
import org.example.aerolinea.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    ReservaRepository reservaRepository;
    @Override
    public ResponseBase saveReserva(Reserva reserva) {
        return new ResponseBase(HttpStatus.CREATED.value(),"Reserva creada", true, Optional.of(reservaRepository.save(reserva)));

    }

    @Override
    public ResponseBase findById(Integer id) {
        Optional<Reserva> reservaBd= reservaRepository.findById(id);
        if(reservaBd.isPresent()){
            return new ResponseBase(HttpStatus.OK.value(), "Reserva encontrada", true, Optional.of(reservaBd.get()));
        }
        return new ResponseBase(400, "Reserva no existe", false, Optional.empty());
    }
}
