package com.codigo.msregistro.domain.impl;

import com.codigo.msregistro.domain.aggregates.dto.PersonaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestPersona;
import com.codigo.msregistro.domain.ports.in.PersonaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceOtherImpl implements PersonaServiceIn {

    @Override
    public PersonaDTO crearPersonaIn(RequestPersona requestPersona) {
        return null;
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return null;
    }

    @Override
    public PersonaDTO actualizarIn(Long id, RequestPersona requestPersona) {
        return null;
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return null;
    }
}
