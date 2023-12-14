package com.lizana.clienteproducto.util;

import com.lizana.clienteproducto.model.externoclient.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Service
public class KafkaRestClientService {

    private final String baseUrl = "http://localhost:8080";
    private final String topicName = "topic-1";
    private final RestTemplate restTemplate;

    public KafkaRestClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public  Mono<ResponseEntity<StatusResponse>> getClient(String tipoDeDocumento, String numeroDeDocumento) {
        String url = baseUrl + "/client"; // Incluye el nombre del tema en la URL

        // Configurar parámetros de la solicitud
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("tipoDeDocumento", tipoDeDocumento);
        queryParams.add("numeroDeDocumento", numeroDeDocumento);

        return Mono.fromCallable(() ->
                restTemplate.getForEntity(url, StatusResponse.class, queryParams)
        ).onErrorResume(throwable -> {

            System.err.println("Error al realizar la solicitud HTTP: " + throwable.getMessage());
            return Mono.empty();
        });
    }
}
