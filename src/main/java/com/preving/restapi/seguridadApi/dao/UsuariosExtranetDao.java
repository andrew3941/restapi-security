package com.preving.restapi.seguridadApi.dao;

public interface UsuariosExtranetDao {

    /**
     * Comprueba si el usuario tiene una sesion abierta
     * @param usuarioId Id del usuario a comprobar
     * @return <b>true</b> si tiene la sesion abierta, <b>false</b> en caso contrario
     */
    boolean hasActiveSession(String usuarioId);

}
