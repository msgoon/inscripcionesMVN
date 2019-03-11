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
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author msgoon6
 */
@Entity
@Table(name = "período")
public class Periodo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int periodo_id;
    @Column(name = "name")
    private String name;
    @Column(name = "ad_client_id")
    private BigDecimal ad_client_id;
    @Column(name = "isactive")
    private char isactive;
    @Column(name = "isnivelacion")
    private char isnivelacion;
    @Column(name = "startdate")
    private Timestamp startdate;
    @Column(name = "enddate")
    private Timestamp enddate;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAd_client_id() {
        return ad_client_id;
    }

    public void setAd_client_id(BigDecimal ad_client_id) {
        this.ad_client_id = ad_client_id;
    }

    public char getIsactive() {
        return isactive;
    }

    public void setIsactive(char isactive) {
        this.isactive = isactive;
    }

    public int getPeriodo_id() {
        return periodo_id;
    }

    public void setPeriodo_id(int periodo_id) {
        this.periodo_id = periodo_id;
    }

    public char getIsnivelacion() {
        return isnivelacion;
    }

    public void setIsnivelacion(char isnivelacion) {
        this.isnivelacion = isnivelacion;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public Periodo(int periodo_id, String name, BigDecimal ad_client_id, char isactive, char isnivelacion, Timestamp startdate, Timestamp enddate) {
        this.periodo_id = periodo_id;
        this.name = name;
        this.ad_client_id = ad_client_id;
        this.isactive = isactive;
        this.isnivelacion = isnivelacion;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Periodo() {
    }

}
