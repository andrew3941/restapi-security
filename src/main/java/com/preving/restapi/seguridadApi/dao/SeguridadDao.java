package com.preving.restapi.seguridadApi.dao;

/**
 * Created by javier-montesinos on 11/05/17.
 */
public interface SeguridadDao {

    /**
     * Comprueba si el usuario y contrase�a proveeidos existen en BBDD. <br>
     * Metodo de autenticacion para usuarios de la <b>Intranet</b>
     * @param username Nombre de usuario
     * @param password Hash generado de la contrase�a
     * @return true si es correcto, false en caso contrario
     */
    boolean authenticateFromIntranet(String username, String password);

}
