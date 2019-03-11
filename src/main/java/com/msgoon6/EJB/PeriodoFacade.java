/*
 * Copyright (C) 2019 migue
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

import com.msgoon6.model.Periodo;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author migue
 */
@Stateless
public class PeriodoFacade extends AbstractFacade<Periodo> implements PeriodoFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodoFacade() {
        super(Periodo.class);
    }

    @Override
    public List<Periodo> findByClientStatusType(boolean active, boolean isnivelacion) {
        Query q = getEntityManager().createNativeQuery("select p.período_id, p.startdate, p.enddate, isactive from período p where ad_client_id = 1000001 and isactive =  ? and isnivelacion = ?;");
        q.setParameter(1, active ? 'Y' : 'N');
        q.setParameter(2, isnivelacion ? 'Y' : 'N');
        List<Object[]> rs = q.getResultList();
        List<Periodo> resp = new ArrayList();
        for (Object[] r : rs) {
            resp.add(new Periodo(Integer.valueOf(r[0].toString()), "", new BigDecimal(1000002), active ? 'Y' : 'N', isnivelacion ? 'Y' : 'N', (Timestamp) r[1], (Timestamp) r[2]));
        }
        return resp;
    }

}
