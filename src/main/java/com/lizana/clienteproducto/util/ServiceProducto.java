package com.lizana.clienteproducto.util;

import com.lizana.clienteproducto.model.externoProduct.StatusResponse;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import com.lizana.clienteproducto.model.PerfilUser;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class ServiceProducto {


    public static Maybe<StatusResponse> serviceProductWc(PerfilUser dto) {

        String url = "http://localhost:8080/product";
        WebClient.Builder builder = WebClient.builder();

        HttpHeaders headers = new HttpHeaders();
        headers.add("productId", dto.getIdDeProducto());


        return Maybe.create(emitter ->
                builder.build()
                        .get()
                        .uri(url)
                        .headers(httpHeaders -> httpHeaders.addAll(headers))
                        .retrieve()
                        .bodyToMono(StatusResponse.class)
                        .subscribe(
                                response -> {
                                    // Enviar el resultado al emisor
                                    emitter.onSuccess(response);
                                },
                                error -> {
                                    // Manejar errores
                                    emitter.onError(error);
                                },
                                () -> {
                                    // No hay más elementos, completar
                                    emitter.onComplete();
                                }
                        )
        );

    }
}