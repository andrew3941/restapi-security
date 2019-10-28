package com.preving.restapi.seguridadApi.service;


import com.preving.restapi.seguridadApi.exceptions.CustomRestApiException;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorCode;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorDetail;
import com.preving.restapi.seguridadApi.domain.crypt.Encryptor;
import org.bouncycastle.crypto.CryptoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rogeliogragera on 15/3/17.
 */
@Service
public class UsuariosManager implements UsuariosService {

    @Value("${frase.autenticacion.intranet}")
    private String fraseAutenticacion;


    public String getHashByUsuarioId(String usuarioId){
        Encryptor e = new Encryptor(fraseAutenticacion);
        try {
            return e.encryptAndHashString(usuarioId);
        } catch (CryptoException e1) {
            e1.printStackTrace();

            List<RestApiErrorDetail> errores = new ArrayList<>();
            errores.add(new RestApiErrorDetail("error.codificacion.unicidad", RestApiErrorCode.ERROR_CODIFICACION_UNICIDAD.getMessage()));
            throw new CustomRestApiException(HttpStatus.INTERNAL_SERVER_ERROR, RestApiErrorCode.ERROR_CODIFICACION_UNICIDAD, errores);
        }
    }



}
