package com.preving.restapi.seguridadApi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(schema ="EXTRANET", name="USUARIOS")
public class ExtranetUser {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String name;

    @Column(name = "APELLIDOS")
    private String surname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOSTRAR")
    private Boolean show;

    @Column(name = "INSERT_BY")
    private Long insertedBy;

    @Column(name = "INSERT_FECHA")
    private Date insertedDate;

    @Column(name = "UPDATE_BY")
    private Long updatedBy;

    @Column(name = "UPDATE_FECHA")
    private Date updateDate;

    @Column(name = "ACTIVO")
    private Boolean enabled;

    @Column(name = "SALTO")
    private String jump;

    @Column(name = "PWD_HASH")
    private String password;

    @Column(name = "VISUALIZA_RMS")
    private Boolean seeRMS;

    @Column(name = "ACCESO_RESTFUL_VS")
    private Boolean RestfulVSAcces;



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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Long getInsertedBy() {
        return insertedBy;
    }

    public void setInsertedBy(Long insertedBy) {
        this.insertedBy = insertedBy;
    }

    public Date getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(Date insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSeeRMS() {
        return seeRMS;
    }

    public void setSeeRMS(Boolean seeRMS) {
        this.seeRMS = seeRMS;
    }

    public Boolean getRestfulVSAcces() {
        return RestfulVSAcces;
    }

    public void setRestfulVSAcces(Boolean restfulVSAcces) {
        RestfulVSAcces = restfulVSAcces;
    }
}
