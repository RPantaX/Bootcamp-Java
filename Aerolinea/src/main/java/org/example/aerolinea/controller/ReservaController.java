package org.example.aerolinea.controller;

import org.example.aerolinea.entity.Reserva;
import org.example.aerolinea.response.ResponseBase;

import org.example.aerolinea.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reserva")
public class ReservaController {

    @Autowired
    ReservaService reservaService;
    @PostMapping("save")
    public ResponseBase saveReserva(@RequestBody Reserva reserva){
        return  reservaService.saveReserva(reserva);
    }
    @GetMapping("get/{id}")
    public ResponseBase findById(@PathVariable Integer id){
        return reservaService.findById(id);
    }
}
