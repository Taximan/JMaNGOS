/*******************************************************************************
 * Copyright (C) 2013 JMaNGOS <http://jmangos.org/>
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.jmangos.world.services.impl;

import java.util.List;

import org.jmangos.commons.entities.ChrClasses;
import org.jmangos.commons.enums.Classes;
import org.jmangos.world.dao.ChrClassesDao;
import org.jmangos.world.services.ChrClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chrClassesService")
public class ChrClassesServiceImpl implements ChrClassesService {

    @Autowired
    private ChrClassesDao chrClassesDao;

    @Override
    public ChrClasses getByClass(final Classes clazz) {
        return this.chrClassesDao.findOne(clazz);
    }

    @Override
    public List<ChrClasses> readChrClasses() {
        return this.chrClassesDao.findAll();
    }

}
