package com.preving.restapi.seguridadApi.exceptions.errors;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enumeration of Error or Exception Keys.
 *
 * @author Leo
 * @since 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RestApiErrorKey {

    // Define las keys de los errores

    CNAE_OBLIGATORIO(1000, "cnae.obligatorio"),

    PROVINCIA_OBLIGATORIO(1001, "provincia.obligatorio"),

    SPA_OBLIGATORIO(1002, "spa.obligatorio"),

    PLAN_OBLIGATORIO(1003, "plan.obligatorio"),

    ESPECIALIDADES_OBLIGATORIO(1004, "especialidades.obligatorio"),

    TRABAJADORES_OBLIGATORIO(1005, "trabajadores.obligatorio"),

    TRABAJADORES_AUTONOMO_ERROR(1006, "trabajadores.autonomo.error"),

    CENTROS_OBLIGATORIO(1007, "centros.obligatorio"),

    CENTROS_AUTONOMO_ERROR(1008, "centros.autonomo.error"),

    RMIC_MENOS_TRABAJADORES(1009, "rmic.menor.trabajadores"),

    RMIC_OBLIGATORIO(1010, "rmic.obligatorio"),

    RMIC_AUTONOMO_ERROR(1011, "rmic.autonomo.error"),

    RMIC_PT(1012, "rmic.pt");

    private final int value;
    private final String key;

    RestApiErrorKey(int value, String key){
        this.value = value;
        this.key = key;
    }

    /**
     * Return the integer value of this rest api code.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Return the message of this rest api code.
     */
    public String getKey() {
        return this.key;
    }

}
