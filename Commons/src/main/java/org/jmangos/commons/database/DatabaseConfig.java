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
package org.jmangos.commons.database;

import org.jmangos.commons.configuration.AbstractConfig;
import org.jmangos.commons.configuration.Property;
import org.springframework.stereotype.Component;

/**
 * This class holds all configuration of database.
 */
@Component
public class DatabaseConfig extends AbstractConfig {

    /**
     * Configuration file location.
     */
    public static final String CONFIG_FILE = "conf/database/database.properties";

    private static final String DEFAULT_CONNECTION = "jdbc:postgresql://localhost:5432/tc";
    private static final String DEFAULT_USERNAME = "postgres";
    private static final String DEFAULT_PASS = "postgres";

    /**
     * Default world driver class to database
     */
    @Property(key = "world.database.driver", defaultValue = "org.postgresql.Driver")
    public String DB_DRIVER;

    /**
     * World database uri !!!with trailing slash!!!
     */
    @Property(key = "world.database.url", defaultValue = DEFAULT_CONNECTION)
    public String DB_CONN_STRING;

    /**
     * World Database Hibernate dialect (see http://hibernate.org)
     */
    @Property(key = "world.database.dialect", defaultValue = "org.hibernate.dialect.PostgreSQLDialect")
    public String DB_DIALECT;

    /**
     * World Database user
     */
    @Property(key = "world.database.user", defaultValue = DEFAULT_USERNAME)
    public String DB_USER;

    /**
     * World Database password
     */
    @Property(key = "world.database.password", defaultValue = DEFAULT_PASS)
    public String DB_PASS;



    public DatabaseConfig() {

        super(CONFIG_FILE);
    }
}
