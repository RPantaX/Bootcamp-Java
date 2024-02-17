package com.codigo.mspantaruiz.infraestructure.adapters;

import com.codigo.mspantaruiz.domain.aggregates.constants.Constants;
import com.codigo.mspantaruiz.domain.aggregates.dto.PersonaDTO;
import com.codigo.mspantaruiz.domain.aggregates.request.RequestPersona;
import com.codigo.mspantaruiz.domain.aggregates.response.ResponseReniec;
import com.codigo.mspantaruiz.domain.ports.out.PersonaServiceOut;
import com.codigo.mspantaruiz.infraestructure.entity.PersonaEntity;
import com.codigo.mspantaruiz.infraestructure.entity.TipoDocumentoEntity;
import com.codigo.mspantaruiz.infraestructure.entity.TipoPersonaEntity;
import com.codigo.mspantaruiz.infraestructure.mapper.PersonaMapper;
import com.codigo.mspantaruiz.infraestructure.repository.PersonaRepository;
import com.codigo.mspantaruiz.infraestructure.repository.TipoDocumentoRepository;
import com.codigo.mspantaruiz.infraestructure.repository.TipoPersonaRepository;
import com.codigo.mspantaruiz.infraestructure.rest.client.ClienteReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {
    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoPersonaRepository tipoPersonaRepository;
    private final PersonaMapper personaMapper;
    private final ClienteReniec reniec;
    @Value("${token.api}")
    private String tokenApi;
    @Override
    public PersonaDTO crearPersonaOut(RequestPersona requestPersona) {
        ResponseReniec datosReniec = getExecutionReniec(requestPersona.getNumDoc());
        personaRepository.save(getEntity(datosReniec,requestPersona));
        return personaMapper.mapToDto(getEntity(datosReniec,requestPersona));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(Long numDoc) {
        return Optional.ofNullable(personaMapper.mapToDto(personaRepository.findByNumDocu(numDoc.toString())));

    }

    @Override
    public List<PersonaDTO> obtenerTodosOut() {
        List<PersonaDTO> personaDTOList = new ArrayList<>();
        List<PersonaEntity> entities = personaRepository.findAllByEstado(1);
        for(PersonaEntity persona : entities){
            PersonaDTO personaDTO = personaMapper.mapToDto(persona);
            personaDTOList.add(personaDTO);
        }
        return personaDTOList;
    }

    @Override
    public PersonaDTO actualizarOut(Long id, RequestPersona requestPersona) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            ResponseReniec responseReniec = getExecutionReniec(requestPersona.getNumDoc());
            personaRepository.save(getEntityUpdate(responseReniec,entity.get()));
            return personaMapper.mapToDto(getEntityUpdate(responseReniec,entity.get()));
        }
        return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            personaRepository.save(entity.get());
            return personaMapper.mapToDto(entity.get());
        }
        return null;
    }

    public ResponseReniec getExecutionReniec(String numero){
        String authorization = "Bearer "+tokenApi;
        return reniec.getInfoReniec(numero,authorization);
    }
    private PersonaEntity getEntity(ResponseReniec reniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDoc());
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipo(requestPersona.getTipoPer());
        return PersonaEntity.builder()
                .numDocu(reniec.getNumeroDocumento())
                .nombres(reniec.getNombres())
                .apeMat(reniec.getApellidoMaterno())
                .apePat(reniec.getApellidoPaterno())
                .estado(Constants.STATUS_ACTIVE)
                .usuaCrea(Constants.AUDIT_ADMIN)
                .dateCreate(getTimestamp())
                .tipoDocumento(tipoDocumento)
                .tipoPersona(tipoPersona)
                .build();

    }
    private PersonaEntity getEntityUpdate(ResponseReniec reniec, PersonaEntity personaActualizar){
        personaActualizar.setNombres(reniec.getNombres());
        personaActualizar.setApePat(reniec.getApellidoPaterno());
        personaActualizar.setApeMat(reniec.getApellidoMaterno());
        personaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        personaActualizar.setDateModif(getTimestamp());
        return personaActualizar;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
}
