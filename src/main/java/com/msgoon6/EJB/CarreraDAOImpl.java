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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author msgoon6
 */
@Stateless
public class CarreraDAOImpl extends AbstractDAO<Carrera> implements CarreraDAO {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraDAOImpl() {
        super(Carrera.class);
    }

    @Override
    public List<Carrera> findAllClientStatusType(boolean isactive, boolean type) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<Carrera> elements = cq.from(Carrera.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(elements.get("ad_client_id"), 1000001));
        predicates.add(cb.equal(elements.get("isactive"), isactive ? 'Y' : 'N'));
        if (type) {
            predicates.add(cb.isNull(elements.get("nivelacion_id")));
        } else {
            predicates.add(cb.isNotNull(elements.get("nivelacion_id")));
        }
        cq.select(elements).where(predicates.toArray(new Predicate[]{}));
        cq.orderBy(cb.asc(elements.get("name")));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
