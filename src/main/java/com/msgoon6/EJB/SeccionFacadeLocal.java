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

import com.msgoon6.model.Seccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author msgoon6
 */
@Local
public interface SeccionFacadeLocal {

    void create(Seccion seccion);

    void edit(Seccion seccion);

    void remove(Seccion seccion);

    Seccion find(Object id);

    List<Seccion> findAll();
    
    List<Seccion> findAllClientStatus(boolean isactive);

    List<Seccion> findRange(int[] range);

    int count();
    
}
