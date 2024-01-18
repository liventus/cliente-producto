package com.lizana.clienteproducto.util;

import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.util.constants.ConstantsUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ServiceClient {

  public static Mono<ResponseEntity<com.lizana.clienteproducto.model.externoclient.StatusResponse>> serviceClientWc(PerfilUser dto){

        String url = ConstantsUtil.URL_CLIENT;
        WebClient.Builder builder = WebClient.builder();

        HttpHeaders headers = new HttpHeaders();
        headers.add(ConstantsUtil.TYPE_OF_DOCUMENT, dto.getTipoDeDocumento());
        headers.add(ConstantsUtil.NUMBER_OF_DOCUMENT, dto.getNumeroDeDocumento());

        return builder.build()
                .get()
                .uri(url)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .toEntity(com.lizana.clienteproducto.model.externoclient.StatusResponse.class)
                .onErrorResume(throwable -> {
                    System.err.println(ConstantsUtil.MESSAGE_ERROR_HTTP + throwable.getMessage());
                    return Mono.empty();
                });

    }





}
