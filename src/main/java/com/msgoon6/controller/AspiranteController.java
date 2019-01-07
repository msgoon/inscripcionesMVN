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

import com.msgoon6.EJB.CarreraFacadeLocal;
import com.msgoon6.EJB.TipoIdentificacionFacadeLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.msgoon6.model.Carrera;
import com.msgoon6.model.TipoIdentificacion;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author msgoon6
 */
@Named
@ViewScoped
public class AspiranteController implements Serializable {

    @EJB
    private TipoIdentificacionFacadeLocal tipoIdentificacionEJB;
    private List<TipoIdentificacion> tiposIdentificacion;

    @PostConstruct
    public void init() {
        tiposIdentificacion = tipoIdentificacionEJB.findAllClientStatus(true);
    }

    public List<TipoIdentificacion> getTiposIdentificacion() {
        return tiposIdentificacion;
    }

    public void setTiposIdentificacion(List<TipoIdentificacion> tiposIdentificacion) {
        this.tiposIdentificacion = tiposIdentificacion;
    }
    
    
}
