package com.lizana.clienteproducto.controller;

import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.model.StatusResponse;
import com.lizana.clienteproducto.service.ClienteProductoService;
import com.lizana.clienteproducto.util.constants.ConstantsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(ConstantsUtil.CLIENT_PRODUCT)
public class ClienteProductoController {

    @Autowired
    ClienteProductoService clienteProductoService;
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public Mono<StatusResponse> create(@RequestBody PerfilUser product) {
        log.info("create client-product");
        return clienteProductoService.save(product);
    }







}
