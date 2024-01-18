package com.lizana.clienteproducto.model.externoclient;

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
public class ClientObject {

    private String id;
    private String tipoDeDocumento;
    private String numeroDeDocumento;
    private String direccion;
    private String tipo;
    private String nombre;
    private String apellido;
    private Boolean clienteVip;
    private String ruc;
    private String razonSocial;
    private Boolean clientePyme;

}
