package com.preving.restapi.seguridadApi.dao;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by javier-montesinos on 11/05/17.
 */
@Repository
public class SpringJdbcSeguridadDao implements SeguridadDao {

    private final int OPTEC_ROL_TECNICO_EXTERNO = 25005;

    @Autowired
    //@Qualifier("jdbcTemplateApp")
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private NamedParameterJdbcTemplate getNamedJdbcTemplate() {
        if (this.namedParameterJdbcTemplate == null) {
            this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        }
        return this.namedParameterJdbcTemplate;
    }

    @Override
    public boolean authenticateFromIntranet(String username, String password) {
        String sql = "SELECT * FROM SSO_SYS.CUENTAS s WHERE (s.USUARIO = :user_name) " +
                "AND (s.ACTIVO = 1) " +

                // Se comprueba que el usuario no tengo el rol TECNICO EXTERNO
                "and not exists (select * from GC2006_RELEASE.pc_usuariosgrupos t " +
                "where t.usuario_id = s.id and t.grupo_id = :rol_tecnico_externo)";

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("user_name", username);
        params.addValue("rol_tecnico_externo", OPTEC_ROL_TECNICO_EXTERNO);

        boolean authenticated = false;

        try{
            Map usuarioMap = this.getNamedJdbcTemplate().queryForMap(sql, params);

            // Concatenar salto y pwd ofrecido
            String pwdAndSalt = password + usuarioMap.get("salto");
            // Encriptar cadena resultante de operacion anterior
            String pwdHashed = DigestUtils.sha1Hex(pwdAndSalt).toUpperCase();

            // hash de pwd ofrecido coincide con el que existe en la base de datos
            if(usuarioMap.get("HASHEDPWD").equals(pwdHashed)){
                authenticated = true;
            }

        } catch(EmptyResultDataAccessException e){
            return false;
        }

        return authenticated;
    }

}
