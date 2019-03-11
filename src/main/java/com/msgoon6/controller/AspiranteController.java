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
import com.msgoon6.EJB.InscripcionFacadeLocal;
import com.msgoon6.EJB.PeriodoFacadeLocal;
import com.msgoon6.EJB.SeccionFacadeLocal;
import com.msgoon6.EJB.TipoColegioFacadeLocal;
import com.msgoon6.EJB.TipoIdentificacionFacadeLocal;
import com.msgoon6.EJB.TipoSangreFacadeLocal;
import com.msgoon6.model.Inscripcion;
import com.msgoon6.model.Periodo;
import com.msgoon6.util.Util;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.primefaces.PrimeFaces;

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
    @EJB
    private InscripcionFacadeLocal inscripcionEJB;
    @EJB
    private PeriodoFacadeLocal periodoEJB;
    private static List<TipoIdentificacion> tiposIdentificacion;
    private static List<Carrera> carreras;
    private static List<Seccion> secciones;
    private static List<TipoColegio> tiposColegio;
    private static List<TipoSangre> tiposSangre;
    private List<String> colegios;
    private String Patron;
    private Inscripcion inscripcion;
    private Date borndate;
    private boolean examen;
    private final String jasperName = "inscripcionOnline.jasper";
    private Periodo actual = null;

    @PostConstruct
    public void init() {
        tiposIdentificacion = tipoIdentificacionEJB.findAllClientStatus(true);
        carreras = carreraEJB.findAllClientStatusType(true, true);
        secciones = seccionEJB.findAllClientStatus(true);
        tiposColegio = tipoColegioEJB.findAllClientStatus(true);
        tiposSangre = tipoSangreEJB.findAllClientStatus(true);
        colegios = inscripcionEJB.findSchools();
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
        inscripcion.setAd_client_id(1000001);
        try {
            Patron = tiposIdentificacion.get(0).getPattern();
        } catch (Exception e) {
            Patron = "********";
        }

        try {
            actual = periodoEJB.findByClientStatusType(true, true).get(0);
        } catch (Exception e) {
            Util.ErrorMessage(ExceptionUtils.getStackTrace(e), ExceptionUtils.getStackTrace(e.getCause()));
        }
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

    public void validateDocument() {
        PrimeFaces current = PrimeFaces.current();
        boolean continueFlag = false;
        if (((TipoIdentificacion) getFromList(inscripcion.getTipo_identificacion().getTipo_identificacion_id(), 4)).getIsvalidated() == 'Y') {
            if (this.validateCI(inscripcion.getTaxid())) {
                continueFlag = true;
            } else {
                Util.ErrorMessage("El Número de Identificación no Cumple el Formato Requerido!", "Verifique los Datos.");
            }
        } else {
            continueFlag = true;
        }
        if (continueFlag) {
            try {
                List<Inscripcion> tmp = inscripcionEJB.findByClientTaxIDCarrer(true, this.inscripcion.getTaxid(), this.inscripcion.getCarrera());
                if (tmp.size() > 0) {
                    borndate = new Date(tmp.get(0).getBorndate().getTime());
                    examen = tmp.get(0).getIstest().equals("Y");
                    this.inscripcion = tmp.get(0);
                }
                current.executeScript("PF('wdlgResultado').show();");
            } catch (Exception e) {
                Util.ErrorMessage(ExceptionUtils.getStackTrace(e), "Contacte al Administrador.");
            }

        }
    }

    public void validateInfo() {
        PrimeFaces current = PrimeFaces.current();
        boolean continueFlag = false;
        if (!continueFlag) {
            try {
                current.executeScript("PF('wdlgResultado2').show();");
            } catch (Exception e) {
                Util.ErrorMessage(ExceptionUtils.getStackTrace(e), "Contacte al Administrador.");
            }
        }
    }

    //Boolean Function to Validate CI
    public boolean validateCI(String taxid) {
        //Variable Definitions
        byte firstPair, thirdDigit, verificator, product, sum = 0, aux;
        byte[] digits = new byte[9];
        //Transformación de cada carácter a un byte
        verificator = Byte.parseByte("" + taxid.charAt(9));
        firstPair = Byte.parseByte(taxid.substring(0, 2));
        thirdDigit = Byte.parseByte("" + taxid.charAt(2));
        for (byte i = 0; i < 9; i++) {
            digits[i] = Byte.parseByte("" + taxid.charAt(i));
        }
        //Verificar segundo dígito
        if (firstPair >= 1 & firstPair <= 24) {
            if (thirdDigit <= 6) {
                //Módulo 10 multiplicar digitos impares por 2
                for (byte i = 0; i < 9; i = (byte) (i + 2)) {
                    product = (byte) (digits[i] * 2);
                    if (product > 9) {
                        product = (byte) (product - 9);
                    }
                    sum = (byte) (sum + product);
                }
                //Módulo 10 multiplicar digitos pares por 1
                for (byte i = 1; i < 9; i = (byte) (i + 2)) {
                    product = (byte) (digits[i] * 1);
                    sum = (byte) (sum + product);
                }
                //Obtener la decena superior de la suma
                aux = sum;
                while (aux % 10 != 0) {
                    aux = (byte) (aux + 1);
                }
                sum = (byte) (aux - sum);
                //Comprobar la suma con dígito verificador (Último Dígito)
                return sum == verificator;
            }
        }
        return false;
    }

    public List<String> completeText(String query) {
        List<String> filtered = new ArrayList<>();
        filtered.add(query);
        for (String col : colegios) {
            if (col.contains(query)) {
                filtered.add(col);
            }
        }

        return filtered;
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

    public void verPDF(Object document, Inscripcion nueva) throws IOException, SQLException {
        File jasper = new File("/usr/share/stanford/reportes/" + jasperName);
        if (System.getProperty("os.name").contains("Windows")) {
            jasper = new File("D:\\Documents\\Desarrollo\\STANFORD\\resources\\" + jasperName);
        }
        Map<String, Object> parameters = new HashMap();
        String dir = jasper.getAbsolutePath().split(jasperName)[0];
        parameters.put("Inscripcion_ID", nueva.getInscripcion_id());
        parameters.put("name", (new java.text.SimpleDateFormat("EEEEE dd 'de' MMMMM 'de' yyyy", new Locale("es", "ES"))).format(actual.getStartdate()) + " - " + (new java.text.SimpleDateFormat("EEEEE dd 'de' MMMMM 'de' yyyy", new Locale("es", "ES"))).format(actual.getEnddate()));
        parameters.put("created", inscripcion.getCreated());
        parameters.put("dir", dir);
        byte[] bytes = inscripcionEJB.generatePDF(jasper, parameters);
        if (bytes != null) {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);

            ServletOutputStream stream = response.getOutputStream();
            stream.write(bytes, 0, bytes.length);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        }

    }

    public void preProcessPDF(Object document) throws IOException {
        try {
            this.inscripcion.setBorndate(new Timestamp(borndate.getTime()));
            this.inscripcion.setIstest(examen ? "Y" : "N");
            this.inscripcion.setPeriodo_id(actual.getPeriodo_id());
            try {
                if (this.inscripcion.getInscripcion_id() > 0) {
                    inscripcionEJB.edit(this.inscripcion);
                } else {
                    inscripcionEJB.create(inscripcion);
                    this.inscripcion.setCarrera((Carrera) getFromList(this.inscripcion.getCarrera().getCarrera_id(), 1));
                    this.inscripcion.setTipo_colegio((TipoColegio) getFromList(this.inscripcion.getTipo_colegio().getTipo_colegio_id(), 2));
                    this.inscripcion.setSeccion((Seccion) getFromList(this.inscripcion.getSeccion().getSeccion_id(), 3));
                }
                verPDF(document, this.inscripcion);
            } catch (IOException | SQLException e) {
                Util.ErrorMessage(ExceptionUtils.getStackTrace(e), "Contacte al Administrador");
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
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
