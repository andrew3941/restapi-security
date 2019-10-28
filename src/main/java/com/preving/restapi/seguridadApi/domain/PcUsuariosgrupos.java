package com.preving.restapi.seguridadApi.domain;

import javax.persistence.*;

@Entity
@Table(name = "PC_USUARIOSGRUPOS", schema = "GC2006_RELEASE", catalog = "")
public class PcUsuariosgrupos {
    @EmbeddedId
    private PcUsuariosGruposPrimaryKey pcUsuariosGruposPrimaryKey;
}
