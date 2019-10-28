package com.preving.restapi.seguridadApi.dao;

import com.preving.restapi.seguridadApi.domain.PcUsuariosGruposPrimaryKey;
import com.preving.restapi.seguridadApi.domain.PcUsuariosgrupos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "pcusuariosgrupos", collectionResourceRel = "pcusuariosgrupos")
public interface PcUsuariosgruposRepository extends JpaRepository<PcUsuariosgrupos, PcUsuariosGruposPrimaryKey> {

}
