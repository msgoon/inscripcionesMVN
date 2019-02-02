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

import com.msgoon6.model.TipoSangre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author msgoon6
 */
@Local
public interface TipoSangreFacadeLocal {

    void create(TipoSangre tipoSangre);

    void edit(TipoSangre tipoSangre);

    void remove(TipoSangre tipoSangre);

    TipoSangre find(Object id);

    List<TipoSangre> findAll();
    
    List<TipoSangre> findAllClientStatus(boolean isactive);

    List<TipoSangre> findRange(int[] range);

    int count();
    
}
