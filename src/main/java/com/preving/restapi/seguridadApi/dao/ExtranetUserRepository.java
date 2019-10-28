package com.preving.restapi.seguridadApi.dao;

import com.preving.restapi.seguridadApi.domain.ExtranetUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtranetUserRepository extends JpaRepository<ExtranetUser, Long> {

    /**
     * Busca en la tabla "EXTRANET.USUARIOS" el usuario
     * @param id Id del usuario a buscar
     * @return Bean representativo de la tabla
     */
    ExtranetUser findExtranetUserById(Long id);
}
