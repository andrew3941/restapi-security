package com.preving.restapi.seguridadApi.exceptions.errors;

import java.io.UnsupportedEncodingException;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by javier-montesinos on 8/03/17.
 */
public class RestApiErrorDetail {

    public final static int TIPO_OBLIGATORIO = 1;
    public final static int TIPO_INCORRECTO = 2;
    public final static int TIPO_TAMANIO = 3;

    private String key;
    private String message;

    public RestApiErrorDetail() {
    }

    public RestApiErrorDetail (String message) {
        this(null, message);
    }

    public RestApiErrorDetail(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public RestApiErrorDetail setKey(String key) {
        this.key = key;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestApiErrorDetail setMessage(String message) {
        try {
            byte[] ptext = message.getBytes("ISO_8859_1");
            this.message = new String(ptext, "UTF-8");
        } catch (UnsupportedEncodingException e){
            this.message = message;
        }
        return this;
    }

    public RestApiErrorDetail setMessageUTF8(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "RestApiErrorDetail{" +
                "key='" + key + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    /**
     * Método que instancia un detalle de error con literal genérico en función al tipo dado.
     *
     * @param key            llave del error
     * @param campoError     campo con error
     * @param tipo           tipo de error
     * @param campoAdicional campo adicional asociado
     * @return {@link RestApiErrorDetail}
     */
    public static RestApiErrorDetail setErrorDetailEstandar(String key, String campoError, int tipo, String campoAdicional) {
        RestApiErrorDetail errorDetail;
        String literal = "";

        if (isNotBlank(key) && isNotBlank(campoError)) {
            switch (tipo) {
                case TIPO_OBLIGATORIO:
                    literal = "El campo " + campoError + " es obligatorio";
                    literal += isNotBlank(campoAdicional) ? " si el campo " + campoAdicional + " se encuentra informado" : "";
                    break;
                case TIPO_INCORRECTO:
                    literal = "El campo " + campoError;
                    literal += isNotBlank(campoAdicional) ? " es incompatible con el campo " + campoAdicional : " es incorrecto";
                    break;
                case TIPO_TAMANIO:
                    literal = "El campo " + campoError + " no tiene la longitud adecuada";
                    literal += isNotBlank(campoAdicional) ? " (" + campoAdicional + ")" : "";
                    break;
            }
            errorDetail = new RestApiErrorDetail(key, literal);

        } else if (isNotBlank(key))
            errorDetail = new RestApiErrorDetail(key, null);
        else
            errorDetail = new RestApiErrorDetail();

        return errorDetail;
    }

    /**
     * @see RestApiErrorDetail#setErrorDetailEstandar(String, String, int, String)
     */
    public static RestApiErrorDetail setErrorDetailEstandar(String key, String campoError, int tipo) {
        return setErrorDetailEstandar(key, campoError, tipo, null);
    }

    /**
     * @see RestApiErrorDetail#setErrorDetailEstandar(String, String, int, String)
     */
    public static RestApiErrorDetail setErrorDetailEstandar(String key, String campoError) {
        return setErrorDetailEstandar(key, campoError, TIPO_OBLIGATORIO, null);
    }

    /**
     * @see RestApiErrorDetail#setErrorDetailEstandar(String, String, int, String)
     */
    public static RestApiErrorDetail setErrorDetailEstandar(String key) {
        return setErrorDetailEstandar(key, null);
    }
}
