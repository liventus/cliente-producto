package com.lizana.clienteproducto.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantsUtil {


  public static final String CLIENT_PRODUCT = "/clienteproducto";
  public static final String URL_CLIENT = "http://api-gateway:8080/client";
  public static final String TYPE_OF_DOCUMENT = "tipoDeDocumento";
  public static final String NUMBER_OF_DOCUMENT = "numeroDeDocumento";
  public static final String MESSAGE_ERROR_HTTP = "Error al realizar la solicitud HTTP: ";

  public static final String URL_PRODUCT = "http://api-gateway:8080";
  public static final String URL_SALDO = "http://api-gateway:8080";


}