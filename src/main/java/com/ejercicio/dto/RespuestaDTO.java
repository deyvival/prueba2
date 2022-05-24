package com.ejercicio.dto;

import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@ToString
public class RespuestaDTO {
    private String tipoRespuesta;
    private String tipoError;
    private String descripcionError;
    private String codigoError;
    public RespuestaDTO(String tipoRespuesta, String tipoError, String descripcionError, String codigoError) {
        super();
        this.tipoRespuesta = tipoRespuesta;
        this.tipoError = tipoError;
        this.descripcionError = descripcionError;
        this.codigoError = codigoError;
    }
    public RespuestaDTO(String tipoRespuesta, String tipoError) {
        this.tipoRespuesta = tipoRespuesta;
        this.tipoError = tipoError;
    }
    public String getTipoRespuesta() {
        return tipoRespuesta;
    }
    public String getTipoError() {
        return tipoError;
    }
    public String getDescripcionError() {
        return descripcionError;
    }
    public String getCodigoError() {
        return codigoError;
    }
}

