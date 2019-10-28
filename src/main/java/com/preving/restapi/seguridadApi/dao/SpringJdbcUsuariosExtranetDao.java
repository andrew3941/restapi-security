package com.preving.restapi.seguridadApi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpringJdbcUsuariosExtranetDao implements UsuariosExtranetDao {

    @Autowired
    //@Qualifier("jdbcTemplateApp")
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean hasActiveSession(String usuarioId) {
        boolean active = false;
        String sql = "SELECT COUNT(USUARIOS.ID) " +
                "FROM EXTRANET.USUARIOS usuarios " +
                "WHERE usuarios.ID like ? " +
                "AND ACTIVO = 1 " +
                "AND EXISTS ( " +
                "    SELECT * " +
                "    FROM EXTRANET.SESIONES sesiones " +
                "    WHERE sesiones.USUARIO_ID = usuarios.id " +
                "    AND sesiones.FCH_FIN IS NULL " +
                "    AND SESIONES.FCH_INICIO > sysdate - interval '5' HOUR) ";
        try {
            Integer result = jdbcTemplate.queryForObject(sql, new Object[]{usuarioId}, Integer.class);
            active = result == 1;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return active;
    }
}
