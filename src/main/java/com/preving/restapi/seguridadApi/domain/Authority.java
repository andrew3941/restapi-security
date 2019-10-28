package com.preving.restapi.seguridadApi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PC_GRUPOS")
public class Authority implements Serializable {

    public static final String ROL_OPERACIONES_ESPECIALES = "gc-25000";
    public static final String ROL_SAC = "gc-2001";
    public static final String ROL_DIRECTOR_TECNICO = "optec-25002";
    public static final String ROL_DIRECTOR_TECNICO2 = "2-25002";
    public static final String GP_RESPONSABLE = "2-25006";
    public static final String ROL_ENDPOINT_FIRMAS = "35-401000";
    public static final String ROL_COMERCIALES = "gc-63";
    public static final String ROL_DIRECTORES_COMERCIALES = "gc-62";


    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "nombre", length = 50)
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private Aplicacion aplicacion;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<IntranetUser> users;

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IntranetUser> getUsers() {
        return users;
    }

    public void setUsers(List<IntranetUser> users) {
        this.users = users;
    }

}