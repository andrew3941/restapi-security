package com.preving.restapi.seguridadApi.dao;

import com.preving.restapi.seguridadApi.domain.IntranetUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by stephan on 20.03.16.
 */
public interface IntranetUserRepository extends JpaRepository<IntranetUser, Long> {

    /**
     * Crea un bean de {@link IntranetUser} con los datos de un usuario de la intranet
     * @param username Username del usuario a cargar
     * @return {@link IntranetUser} con los datos
     */
    IntranetUser findUserByUsername(String username);

    /**
     * Crea un bean de {@link IntranetUser} con los datos de un usuario de la intranet
     * @param id Id del usuario a cargar
     * @return {@link IntranetUser} con los datos
     */
    IntranetUser findUserById(long id);
}
