package com.codigo.msregistro.infraestructure.adapters;

import com.codigo.msregistro.domain.aggregates.dto.PersonaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestPersona;
import com.codigo.msregistro.domain.aggregates.response.ResponseReniec;
import com.codigo.msregistro.infraestructure.entity.PersonaEntity;
import com.codigo.msregistro.infraestructure.entity.TipoDocumentoEntity;
import com.codigo.msregistro.infraestructure.mapper.PersonaMapper;
import com.codigo.msregistro.infraestructure.redis.RedisService;
import com.codigo.msregistro.infraestructure.repository.PersonaRepository;
import com.codigo.msregistro.infraestructure.repository.TipoDocumentoRepository;
import com.codigo.msregistro.infraestructure.rest.client.ClienteReniec;
import com.codigo.msregistro.infraestructure.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
class PersonaAdapterOtherTest {
    @Mock
    private  PersonaRepository personaRepository;
    @Mock
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Mock
    private PersonaMapper personaMapper;
    @Mock
    private ClienteReniec reniec;
    @Mock
    private RedisService redisService;
    @Mock
    private Util util;

    @InjectMocks
    private PersonaAdapterOther personaAdapterOther;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void crearPersonaOutSuccess() {
        // Mock para RequestPersona
        RequestPersona requestPersona = new RequestPersona("DNI", "720320033213213213");
        ResponseReniec infoReniec =  getResponseReniec();
        PersonaDTO personaDTOEsperada = getPersonaDTO();
        //personaDTOEsperada.setIdPersona(1L);
        ReflectionTestUtils.setField(personaAdapterOther,"tokenApi","XXXXXXXX",String.class);

        //Comportamiento
        when(reniec.getInfoReniec(anyString(), anyString())).thenReturn(infoReniec);
        when(personaMapper.mapToDto(any())).thenReturn(personaDTOEsperada);

        PersonaDTO personaDTORecibida = personaAdapterOther.crearPersonaOut(requestPersona);
        // Verificar resultado

        //assertDoesNotThrow(() -> personaAdapter.crearPersonaOut(requestPersona));
        assertEquals(personaDTOEsperada.getIdPersona(),personaDTORecibida.getIdPersona());
        assertEquals(personaDTOEsperada.getNombres(),personaDTORecibida.getNombres());
        assertEquals(personaDTOEsperada.getEstado(),personaDTORecibida.getEstado());

        //assertEquals(valorEsperas, valorActualqueRecibes); -- DEBEN SER IGUALES
        //assertNotEquals(valorEsperas,valorActualqueRecibes); -- NO SEAN IGUALES

    }



    @Test
    void crearPersonaOutError() {
        // Mock para RequestPersona
        RequestPersona requestPersona = new RequestPersona("DNI", "720320033");
        ResponseReniec datosReniec =  getResponseReniec();
        ReflectionTestUtils.setField(personaAdapterOther,"tokenApi","XXXXXXXX",String.class);

        //Comportamiento
        when(reniec.getInfoReniec(anyString(), anyString())).thenThrow(RuntimeException.class);
        // Verificar resultado
        assertThrows(Exception.class,() -> personaAdapterOther.crearPersonaOut(requestPersona));
    }

    @Test
    void obtenerPersonaOut_whenPersonaExists_returnsPersonaDTO() {
        Long id = 1L;
        PersonaEntity entity = new PersonaEntity();
        Optional<PersonaEntity> optionalEntity = Optional.of(entity);
        PersonaDTO expectedDTO = new PersonaDTO();

        when(personaRepository.findById(id)).thenReturn(optionalEntity);
        when(personaMapper.mapToDto(entity)).thenReturn(expectedDTO);

        Optional<PersonaDTO> result = personaAdapterOther.obtenerPersonaOut(id);

        assertTrue(result.isPresent());
        assertEquals(expectedDTO, result.get());
    }


    @Test
    void obtenerPersonaOut_whenPersonaDoesNotExist_returnsEmptyOptional() {
        Long id = 2L;
        when(personaRepository.findById(id)).thenReturn(Optional.empty());
        Optional<PersonaDTO> result = personaAdapterOther.obtenerPersonaOut(id);
        assertTrue(result.isEmpty());
    }
    @Test
    void obtenerTodosOut_whenPersonasExist_returnsPersonaDTOList() {
        List<PersonaEntity> entities = new ArrayList<>();
        // Add your desired number of PersonaEntity objects with necessary fields set
        entities.add(new PersonaEntity());
        entities.add(new PersonaEntity());
        List<PersonaDTO> expectedDTOs = new ArrayList<>();
        for (PersonaEntity entity : entities) {
            expectedDTOs.add(new PersonaDTO()); // Set expected DTO fields based on entity
        }

        when(personaRepository.findAll()).thenReturn(entities);
        when(personaMapper.mapToDto(entities.get(0))).thenReturn(expectedDTOs.get(0));
        when(personaMapper.mapToDto(entities.get(1))).thenReturn(expectedDTOs.get(1));

        List<PersonaDTO> actualDTOs = personaAdapterOther.obtenerTodosOut();

        assertEquals(expectedDTOs.size(), actualDTOs.size());
        for (int i = 0; i < expectedDTOs.size(); i++) {
            assertEquals(expectedDTOs.get(i), actualDTOs.get(i));
        }
    }
    @Test
    void obtenerTodosOut_whenNoPersonasExist_returnsEmptyList() {
        when(personaRepository.findAll()).thenReturn(Collections.emptyList());
        List<PersonaDTO> actualDTOs = personaAdapterOther.obtenerTodosOut();
        assertTrue(actualDTOs.isEmpty());
    }

    @Test
    void actualizarOut() {
    }

    @Test
    void deleteOut() {
    }

    private ResponseReniec getResponseReniec(){
        return new ResponseReniec("Juan", "Perez", "Lopez", "DNI","720320083","2");
    }

    private PersonaDTO getPersonaDTO(){
        return new PersonaDTO(1L,"72032003","Juan","Perez","Lopez",1,"admin",getTimestamp(),"admin",getTimestamp(),"admin",getTimestamp());
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
    private PersonaEntity getPersonaEntity(){
        return new PersonaEntity(1L,
                "76543210",
                "Juan Carlos",
                "García",
                "Pérez",
                1, // Asumiendo que 1 representa "activo"
                "admin",
                new Timestamp(System.currentTimeMillis()),
                new TipoDocumentoEntity(1, "DNI") // Asumiendo que TipoDocumentoEntity existe
        );
    }

}