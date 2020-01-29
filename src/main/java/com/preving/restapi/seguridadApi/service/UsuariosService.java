package com.preving.restapi.seguridadApi.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rogeliogragera on 15/3/17.
 */
public interface UsuariosService {

    String getHashByUsuarioId(String usuarioId);

    Authentication getAuthentication(Authentication authentication);

    void securityFilter(HttpServletRequest request);

}
