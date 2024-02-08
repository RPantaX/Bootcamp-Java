package com.codigo.feign.service.impl;

import com.codigo.feign.aggregates.constants.Constants;
import com.codigo.feign.aggregates.request.PersonaRequest;
import com.codigo.feign.aggregates.response.BaseResponse;
import com.codigo.feign.aggregates.response.ReniecResponse;
import com.codigo.feign.feignClient.ReniecClient;
import com.codigo.feign.repository.PersonaRepository;
import com.codigo.feign.service.PersonaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaSerciveImpl implements PersonaService {
    private final PersonaRepository personaRepository;
    private final ReniecClient reniecClient;

    public PersonaSerciveImpl(PersonaRepository personaRepository, ReniecClient reniecClient) {
        this.personaRepository = personaRepository;
        this.reniecClient = reniecClient;
    }
    @Value("${token.api}")
    private String tokenApi;
    @Override
    public BaseResponse crearPersona(PersonaRequest personaRequest) {
        return null;
    }

    @Override
    public BaseResponse getInfoReniec(String numero) {
        ReniecResponse response=getExecution(numero);
        if(response != null){
            return new BaseResponse(Constants.CODE_SUCCES, Constants.MESS_SUCCESS, Optional.of(response));
        } else {
            return new BaseResponse(Constants.CODE_ERROR, Constants.MESS_ERROR, Optional.empty());
        }
    }

    public ReniecResponse getExecution(String numero){
        String authorization = "Bearer " + tokenApi;
        return reniecClient.getInfo(numero, authorization);
    }
}
