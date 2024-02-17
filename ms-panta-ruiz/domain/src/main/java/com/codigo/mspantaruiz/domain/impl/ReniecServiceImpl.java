package com.codigo.mspantaruiz.domain.impl;

import com.codigo.mspantaruiz.domain.aggregates.response.ResponseReniec;
import com.codigo.mspantaruiz.domain.ports.in.ReniecServiceIn;
import com.codigo.mspantaruiz.domain.ports.out.RestReniecOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReniecServiceImpl implements ReniecServiceIn {
    private final RestReniecOut reniec;

    @Override
    public ResponseReniec getInfoIn(String numDoc) {
        return reniec.getInfoReniec(numDoc);
    }
}
