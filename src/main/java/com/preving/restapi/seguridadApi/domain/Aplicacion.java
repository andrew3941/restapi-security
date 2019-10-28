package com.preving.restapi.seguridadApi.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Creado por David el día 16/05/2017.
 */
@Entity
@Table(name = "APLICACIONES", schema = "SSO_SYS")
public class Aplicacion {

    @Id
    private int id;

    @Column(name = "codigo_app")
    private String codigoApp;

    @OneToMany(mappedBy = "aplicacion", fetch = FetchType.LAZY)
    private Set<Authority> authoritySet;

    public Aplicacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoApp() {
        return codigoApp;
    }

    public void setCodigoApp(String codigoApp) {
        this.codigoApp = codigoApp;
    }
}

