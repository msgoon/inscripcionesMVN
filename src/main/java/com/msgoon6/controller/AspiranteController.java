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
package com.msgoon6.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.msgoon6.model.TipoIdentificacion;
import com.msgoon6.model.Carrera;
import java.io.Serializable;
import java.util.List;
import com.msgoon6.EJB.CarreraDAO;
import com.msgoon6.EJB.TipoIdentificacionDAO;

/**
 *
 * @author msgoon6
 */
@Named
@ViewScoped
public class AspiranteController implements Serializable {

    @EJB
    private TipoIdentificacionDAO tipoIdentificacionEJB;
    @EJB
    private CarreraDAO carreraEJB;
    private List<TipoIdentificacion> tiposIdentificacion;
    private List<Carrera> carreras;

    @PostConstruct
    public void init() {
        tiposIdentificacion = tipoIdentificacionEJB.findAllClientStatus(true);
        carreras = carreraEJB.findAllClientStatusType(true, true);
    }

    public List<TipoIdentificacion> getTiposIdentificacion() {
        return tiposIdentificacion;
    }

    public void setTiposIdentificacion(List<TipoIdentificacion> tiposIdentificacion) {
        this.tiposIdentificacion = tiposIdentificacion;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
    
    
}
