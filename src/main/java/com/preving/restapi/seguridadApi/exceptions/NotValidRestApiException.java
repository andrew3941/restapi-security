package com.preving.restapi.seguridadApi.exceptions;

import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorCode;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorDetail;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by javier-montesinos on 8/03/17.
 */
public class NotValidRestApiException extends CustomRestApiException {


    public NotValidRestApiException(RestApiErrorCode code) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code);
    }

    public NotValidRestApiException(RestApiErrorCode code, String customMessage) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, customMessage);
    }

    public NotValidRestApiException(RestApiErrorCode code, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, errorsDetail);
    }

    public NotValidRestApiException(RestApiErrorCode code, String customMessage, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, customMessage, errorsDetail);
    }
}
