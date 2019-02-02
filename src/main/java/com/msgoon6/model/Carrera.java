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
@Table(name = "carrera")
public class Carrera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carrera_id;
    @Column(name = "name")
    private String name;
    @Column(name = "ad_client_id")
    private BigDecimal ad_client_id;
    @Column(name = "isactive")
    private char isactive;
    @ManyToOne
    @JoinColumn(name = "nivelacion_id")
    private Carrera nivelacion_id;

    public int getCarrera_id() {
        return carrera_id;
    }

    public void setCarrera_id(int carrera_id) {
        this.carrera_id = carrera_id;
    }

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

    public Carrera getNivelacion_id() {
        return nivelacion_id;
    }

    public void setNivelacion_id(Carrera nivelacion_id) {
        this.nivelacion_id = nivelacion_id;
    }

}
