package com.preving.restapi.seguridadApi.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface ExtranetUserService {

    /**
     * Comprueba si el usuario tiene una sesion activa
     * @param usuarioId Id del usuario
     * @return <b>true</b> si tiene una sesion activa, <b>false</b> en caso contrario
     */
    boolean hasActiveSession(String usuarioId);

    /**
     * Crea un bean de UserDetail para el servicio de JWT con los datos de un usuario de la extranet
     * @param id Id del usuario a buscar
     * @param hash Hash del usuario para asignarlo al token JWT
     * @return Datos del usuario para generar el token JWT
     */
    UserDetails findUserById(String id, String hash);

    /**
     * Calcula el hash del id usuario
     * @param usuarioId Id del usuario
     * @return String representativo del hash
     */
    String getHashByUsuarioId(String usuarioId);
}
