package com.preving.restapi.seguridadApi.exceptions;

import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorCode;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorDetail;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by javier-montesinos on 8/03/17.
 */
public class BadRequestRestApiException extends CustomRestApiException {


    public BadRequestRestApiException(RestApiErrorCode code) {
        super(HttpStatus.BAD_REQUEST, code);
    }

    public BadRequestRestApiException(RestApiErrorCode code, String customMessage) {
        super(HttpStatus.BAD_REQUEST, code, customMessage);
    }

    public BadRequestRestApiException(RestApiErrorCode code, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.BAD_REQUEST, code, errorsDetail);
    }

    public BadRequestRestApiException(RestApiErrorCode code, String customMessage, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.BAD_REQUEST, code, customMessage, errorsDetail);
    }
}
