package com.codigo.mspantaruiz.domain.ports.in;

import com.codigo.mspantaruiz.domain.aggregates.response.ResponseReniec;

public interface ReniecServiceIn {
    ResponseReniec getInfoIn(String numDoc);
}
