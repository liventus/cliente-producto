package com.lizana.clienteproducto.util;


import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.util.constants.ConstantsUtil;
import org.springframework.http.HttpHeaders;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;




public class ServiceProducto {
    public static Mono<com.lizana.clienteproducto.model.externoproduct.StatusResponse> serviceProductWc(PerfilUser dto){

        String url = ConstantsUtil.URL_PRODUCT.concat("/product");
        WebClient.Builder builder = WebClient.builder();

        HttpHeaders headers = new HttpHeaders();
        headers.add("productId", dto.getIdDeProducto());


        return builder.build()
            .get()
            .uri(url)
            .headers(httpHeaders -> httpHeaders.addAll(headers))
            .retrieve().bodyToMono(com.lizana.clienteproducto.model.externoproduct.StatusResponse.class);



    }
}