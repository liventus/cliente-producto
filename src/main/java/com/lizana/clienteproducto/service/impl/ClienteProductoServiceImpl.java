package com.lizana.clienteproducto.service.impl;

import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.model.StatusResponse;
import com.lizana.clienteproducto.model.externosaldo.SaldoDto;
import com.lizana.clienteproducto.service.ClienteProductoService;
import com.lizana.clienteproducto.util.ServiceClient;
import com.lizana.clienteproducto.util.ServiceProducto;
import com.lizana.clienteproducto.util.ServiceSaldo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ClienteProductoServiceImpl implements ClienteProductoService {

    @Override
    public Mono<StatusResponse> save(PerfilUser dto) {
      log.info("ClienteProductoServiceImpl inicio");
      Mono<ResponseEntity<com.lizana.clienteproducto.model.externoclient.StatusResponse>> responseClient= ServiceClient.serviceClientWc(dto);
      Mono<com.lizana.clienteproducto.model.externoproduct.StatusResponse> responseProduct = ServiceProducto.serviceProductWc(dto);
      log.info("2");
      return Mono.zip(responseClient,responseProduct)
          .flatMap(tuple -> {
            return metodoGrabar(tuple.getT1(),tuple.getT2(),dto);
          });
    }


  private Mono<StatusResponse> metodoGrabar(ResponseEntity<com
            .lizana.clienteproducto.model.externoclient.StatusResponse> valueCliente, com.lizana.clienteproducto.model
            .externoproduct.StatusResponse valueProduct,PerfilUser dto) {
    log.info("3");
        SaldoDto saldoDto = new SaldoDto();
        saldoDto.setProducto(valueProduct.getDetail().getId());
        saldoDto.setCliente(valueCliente.getBody().getDetail().getId());
        saldoDto.setFirmante(dto.getFirmante());
        saldoDto.setTitular(dto.getTitular());
        saldoDto.setSaldo(0);

        return ServiceSaldo.serviceSaldoWc(saldoDto)
                .map(statusResponse -> {
                    StatusResponse s = new StatusResponse();
                    s.setCode(statusResponse.getCode());
                    s.setDescription(statusResponse.getDescription());
                    s.setDetail(statusResponse.getDetail());
                    return s;
                });
    }
}
