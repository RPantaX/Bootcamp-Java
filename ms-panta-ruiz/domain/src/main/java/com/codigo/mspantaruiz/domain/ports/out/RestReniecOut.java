package com.codigo.mspantaruiz.domain.ports.out;

import com.codigo.mspantaruiz.domain.aggregates.response.ResponseReniec;

public interface RestReniecOut {
    ResponseReniec getInfoReniec(String numDoc);
}
