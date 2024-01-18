package com.lizana.clienteproducto.service;

import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.model.StatusResponse;
import reactor.core.publisher.Mono;

public interface ClienteProductoService {

    Mono<StatusResponse> save(PerfilUser ProductObject);

}
