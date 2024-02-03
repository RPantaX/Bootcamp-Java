package org.example.aerolinea.service;

import org.example.aerolinea.entity.Reserva;
import org.example.aerolinea.response.ResponseBase;

import java.util.Optional;

public interface ReservaService {
    ResponseBase saveReserva(Reserva reserva);
    ResponseBase findById(Integer id);
}
