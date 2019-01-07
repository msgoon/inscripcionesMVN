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
import com.msgoon6.model.Seccion;
import com.msgoon6.model.TipoColegio;
import com.msgoon6.model.TipoSangre;
import java.io.Serializable;
import java.util.List;
import com.msgoon6.EJB.CarreraFacadeLocal;
import com.msgoon6.EJB.SeccionFacadeLocal;
import com.msgoon6.EJB.TipoColegioFacadeLocal;
import com.msgoon6.EJB.TipoIdentificacionFacadeLocal;
import com.msgoon6.EJB.TipoSangreFacadeLocal;
import com.msgoon6.model.Inscripcion;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author msgoon6
 */
@Named
@ViewScoped
public class AspiranteController implements Serializable {

    @EJB
    private TipoIdentificacionFacadeLocal tipoIdentificacionEJB;
    @EJB
    private CarreraFacadeLocal carreraEJB;
    @EJB
    private SeccionFacadeLocal seccionEJB;
    @EJB
    private TipoColegioFacadeLocal tipoColegioEJB;
    @EJB
    private TipoSangreFacadeLocal tipoSangreEJB;
    private static List<TipoIdentificacion> tiposIdentificacion;
    private static List<Carrera> carreras;
    private static List<Seccion> secciones;
    private static List<TipoColegio> tiposColegio;
    private static List<TipoSangre> tiposSangre;
    private String Patron;
    private Inscripcion inscripcion;
    private Date borndate;
    private boolean examen;

    @PostConstruct
    public void init() {
        tiposIdentificacion = tipoIdentificacionEJB.findAllClientStatus(true);
        carreras = carreraEJB.findAllClientStatusType(true, true);
        secciones = seccionEJB.findAllClientStatus(true);
        tiposColegio = tipoColegioEJB.findAllClientStatus(true);
        tiposSangre = tipoSangreEJB.findAllClientStatus(true);
        inscripcion = new Inscripcion();
        inscripcion.setInscripcion_id(0);
        inscripcion.setApellido("");
        inscripcion.setNombreestudiante("");
        inscripcion.setAddress("");
        inscripcion.setPhone("");
        inscripcion.setBorndate(new Timestamp(new Date().getTime()));
        inscripcion.setMailtext("");
        inscripcion.setTiposangre(new TipoSangre());
        inscripcion.setInstitucionprocedencia("");
        inscripcion.setTipo_colegio(new TipoColegio());
        inscripcion.setIstest("N");
        inscripcion.setCarrera(new Carrera());
        inscripcion.setSeccion(new Seccion());
        inscripcion.setTipo_identificacion(new TipoIdentificacion());
        inscripcion.setTaxid("");
        inscripcion.setPhone2("");
        inscripcion.setIsactive("Y");
        inscripcion.setNumero(0);
        Patron = tiposIdentificacion.get(0).getPattern();
        borndate = new Date();
        examen = false;
    }

    public String getPatron() {
        try {
            String tmp = ((TipoIdentificacion) getFromList(inscripcion.getTipo_identificacion().getTipo_identificacion_id(), 4)).getPattern();
            if (tmp != null) {
                return tmp;
            } else {
                return Patron;
            }
        } catch (Exception e) {
            return Patron;
        }
    }

    private static Object getFromList(Integer obj, Integer pivote) {
        Object resp = null;

        switch (pivote) {
            case 1: {
                for (Carrera car : carreras) {
                    if (obj.equals(car.getCarrera_id())) {
                        resp = (Object) car;
                    }
                }
            }
            break;
            case 2: {
                for (TipoColegio col : tiposColegio) {
                    if (obj.equals(col.getTipo_colegio_id())) {
                        resp = (Object) col;
                    }
                }
            }
            break;
            case 3: {
                for (Seccion sec : secciones) {
                    if (obj.equals(sec.getSeccion_id())) {
                        resp = (Object) sec;
                    }
                }
            }
            break;
            case 4: {
                for (TipoIdentificacion tid : tiposIdentificacion) {
                    if (tid.getTipo_identificacion_id().equals(obj)) {
                        resp = (Object) tid;
                    }
                }
            }
            break;
        }

        return resp;
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

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public List<TipoColegio> getTiposColegio() {
        return tiposColegio;
    }

    public void setTiposColegio(List<TipoColegio> tiposColegio) {
        this.tiposColegio = tiposColegio;
    }

    public List<TipoSangre> getTiposSangre() {
        return tiposSangre;
    }

    public void setTiposSangre(List<TipoSangre> tiposSangre) {
        this.tiposSangre = tiposSangre;
    }

    public void setPatron(String Patron) {
        this.Patron = Patron;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Date getBorndate() {
        return borndate;
    }

    public void setBorndate(Date borndate) {
        this.borndate = borndate;
    }

    public boolean isExamen() {
        return examen;
    }

    public void setExamen(boolean examen) {
        this.examen = examen;
    }

}
