package com.lizana.clienteproducto.controller;




import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.model.StatusResponse;
import com.lizana.clienteproducto.service.ClienteProductoService;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ClienteProductoController.CLIENTEPRODUCTO)
public class ClienteProductoController {
    public static final String CLIENTEPRODUCTO = "/clienteproducto";

    @Autowired
    ClienteProductoService clienteProductoService;

    @PostMapping(produces = {"application/json"})
    public Maybe<StatusResponse> create(@RequestBody PerfilUser product) {
        return clienteProductoService.saveProduct(product);
    }
    @PostMapping("/wc")
    public Maybe<StatusResponse> createwc(@RequestBody PerfilUser product) {
        return clienteProductoService.saveProductwc(product);
    }







}
