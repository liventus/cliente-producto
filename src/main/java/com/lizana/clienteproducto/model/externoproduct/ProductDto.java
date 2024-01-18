package com.lizana.clienteproducto.model.externoproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private String id;
    private String idDeProducto;
    private Number comision;
    private String tipoDeCuenta;
    private String claseDeCuenta;
    private Number limitMaxDepositoMensual;
    private Number limitMaxRetiroMensual;
    private Number limitMaxPagoMensual;
    private Number limitMaxCredito;



}
