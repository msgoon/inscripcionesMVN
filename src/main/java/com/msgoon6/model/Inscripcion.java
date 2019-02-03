/*
 * Copyright (C) 2019 msgoon6
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.msgoon6.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author msgoon6
 */
@Entity
@Table(name = "mm_inscripcion_tmp")
public class Inscripcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscripcion_id")
    private Integer inscripcion_id;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "nombreestudiante")
    private String nombreestudiante;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "borndate")
    private Timestamp borndate;
    @Column(name = "mailtext")
    private String mailtext;
    @Column(name = "institucionprocedencia")
    private String institucionprocedencia;
    @Column(name = "istest")
    private String istest;

    @ManyToOne
    @JoinColumn(name = "tiposangre_id")
    private TipoSangre tiposangre;
    @ManyToOne
    @JoinColumn(name = "tipo_colegio_id")
    private TipoColegio tipo_colegio;
    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;
    @ManyToOne
    @JoinColumn(name = "seccion_id")
    private Seccion seccion;
    @ManyToOne
    @JoinColumn(name = "tipo_identificacion_id")
    private TipoIdentificacion tipo_identificacion;
    @Column(name = "taxid")
    private String taxid;
    @Column(name = "phone2")
    private String phone2;
    @Column(name = "isactive")
    private String isactive;
    private Integer numero;
    @Column(name = "ad_client_id")
    private Integer ad_client_id;
    
    public Integer getInscripcion_id() {
        return inscripcion_id;
    }

    public void setInscripcion_id(Integer inscripcion_id) {
        this.inscripcion_id = inscripcion_id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreestudiante() {
        return nombreestudiante;
    }

    public void setNombreestudiante(String nombreestudiante) {
        this.nombreestudiante = nombreestudiante;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getBorndate() {
        return borndate;
    }

    public void setBorndate(Timestamp borndate) {
        this.borndate = borndate;
    }

    public String getMailtext() {
        return mailtext;
    }

    public void setMailtext(String mailtext) {
        this.mailtext = mailtext;
    }

    public String getInstitucionprocedencia() {
        return institucionprocedencia;
    }

    public void setInstitucionprocedencia(String institucionprocedencia) {
        this.institucionprocedencia = institucionprocedencia;
    }

    public String getIstest() {
        return istest;
    }

    public void setIstest(String istest) {
        this.istest = istest;
    }

    public TipoSangre getTiposangre() {
        return tiposangre;
    }

    public void setTiposangre(TipoSangre tiposangre) {
        this.tiposangre = tiposangre;
    }

    public TipoColegio getTipo_colegio() {
        return tipo_colegio;
    }

    public void setTipo_colegio(TipoColegio tipo_colegio) {
        this.tipo_colegio = tipo_colegio;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public TipoIdentificacion getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(TipoIdentificacion tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getAd_client_id() {
        return ad_client_id;
    }

    public void setAd_client_id(Integer ad_client_id) {
        this.ad_client_id = ad_client_id;
    }
    
}
