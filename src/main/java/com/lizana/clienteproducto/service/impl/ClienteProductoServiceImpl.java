package com.lizana.clienteproducto.service.impl;

import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.model.StatusResponse;
import com.lizana.clienteproducto.service.ClienteProductoService;
import com.lizana.clienteproducto.util.ServiceClient;
import com.lizana.clienteproducto.util.KafkaRestClientService;
import com.lizana.clienteproducto.util.ServiceProducto;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ClienteProductoServiceImpl implements ClienteProductoService {






    @Autowired
    KafkaRestClientService kafkaRestClientService;




    @Override
    public  Maybe<StatusResponse> saveProduct(PerfilUser dto) {

        System.out.println("inicio save:");


        Mono<ResponseEntity<com.lizana.clienteproducto.model.externoClient.StatusResponse>> restuestakasfka =
                kafkaRestClientService.getClient(dto.getTipoDeDocumento(), dto.getNumeroDeDocumento());
        restuestakasfka.subscribe(responseEntity -> {
            com.lizana.clienteproducto.model.externoClient.StatusResponse statusResponse = responseEntity.getBody();
            System.out.println("Respuesta de Kafka: " + statusResponse);
        }, throwable -> {
            // Manejar errores de manera reactiva
            System.err.println("Error al obtener la respuesta de Kafka: " + throwable.getMessage());
        });





        StatusResponse statusResponse = new StatusResponse();
        return Maybe.just(statusResponse);
    }

    @Override
    public Maybe<StatusResponse> saveProductwc(PerfilUser dto) {

        System.out.println("inicio save:wc ");

        Mono<ResponseEntity<com.lizana.clienteproducto.model.externoClient.StatusResponse>> responseClient = ServiceClient.serviceClientWc(dto);
        responseClient.doOnSuccess(statusResponse -> {
            System.out.println(statusResponse.getBody().getCode());
            System.out.println(statusResponse.getBody().getDescription());
            System.out.println(statusResponse.getBody().getDetail().toString());
        })
                .doOnError(error -> {
                    // Manejar errores
                    System.err.println("Error al procesar la respuesta: " + error.getMessage());
                })
                .doOnTerminate(() -> {
                    // Acciones cuando la secuencia se completa
                    System.out.println("La secuencia se ha completado.");
                })
                .subscribe();




        Maybe<com.lizana.clienteproducto.model.externoProduct.StatusResponse> responseProduct = ServiceProducto.serviceProductWc(dto);
        responseProduct
                .doOnSuccess(statusResponse -> {
                    System.out.println(statusResponse.getCode());
                    System.out.println(statusResponse.getDescription());
                    System.out.println(statusResponse.getDetail().toString());
                })
                .doOnError(error -> {
                    // Manejar errores
                    System.err.println("Error al procesar la respuesta: " + error.getMessage());
                })
                .doOnComplete(() -> {
                    // Acciones cuando la secuencia se completa
                    System.out.println("La secuencia se ha completado.");
                })
                .subscribe();





        StatusResponse statusResponse = new StatusResponse();
        return Maybe.just(statusResponse);
    }
}
