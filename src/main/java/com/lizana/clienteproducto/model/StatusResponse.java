package com.lizana.clienteproducto.model;


import com.lizana.clienteproducto.model.externoClient.ClientObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {
    private Integer code;
    private String description;
    private ClientObject detail;

}


