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
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author msgoon6
 */
@Local
public interface InscripcionFacadeLocal {

    void create(Inscripcion inscripcion);

    void edit(Inscripcion inscripcion);

    void remove(Inscripcion inscripcion);

    Inscripcion find(Object id);

    List<Inscripcion> findAll();
    
    List<Inscripcion> findByClientTaxIDCarrer(boolean isactive, String taxid, Carrera carrer);
    
    List<String> findSchools();

    List<Inscripcion> findRange(int[] range);

    int count();
    
    byte[] generatePDF(File jasper, Map<String, Object> parameters);
    
}
