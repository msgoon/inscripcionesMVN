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
package com.msgoon6.EJB;

import com.msgoon6.model.Carrera;
import com.msgoon6.model.Inscripcion;
import com.msgoon6.util.Util;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author msgoon6
 */
@Stateless
public class InscripcionFacade extends AbstractFacade<Inscripcion> implements InscripcionFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InscripcionFacade() {
        super(Inscripcion.class);
    }

    @Override
    public List<Inscripcion> findByClientTaxIDCarrer(boolean active, String taxid, Carrera carrer) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Inscripcion> elements = cq.from(Inscripcion.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(elements.get("ad_client_id"), 1000001));
        predicates.add(cb.equal(elements.get("isactive"), active ? 'Y' : 'N'));
        predicates.add(cb.equal(elements.get("taxid"), taxid));
        predicates.add(cb.equal(elements.get("carrera"), carrer));
        cq.select(elements).where(predicates.toArray(new Predicate[]{}));
        cq.orderBy(cb.asc(elements.get("apellido")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<String> findSchools() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Inscripcion> elements = cq.from(Inscripcion.class);
        cq.select(elements.get("institucionprocedencia")).distinct(true);
        cq.orderBy(cb.asc(elements.get("institucionprocedencia")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public byte[] generatePDF(File jasper, Map<String, Object> parameters) {
        Connection conexion = null;
        try {
            conexion = em.unwrap(java.sql.Connection.class);
            byte[] resp = JasperRunManager.runReportToPdf(jasper.getPath(), parameters, conexion);
            conexion.close();
            return resp;
        } catch (SQLException | JRException e) {
            Util.ErrorMessage(ExceptionUtils.getStackTrace(e), "Comuniquese con el Administrador");
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                Util.ErrorMessage(ExceptionUtils.getStackTrace(e), "Comuniquese con el Administrador");
            }
        }
        return null;
    }

}
