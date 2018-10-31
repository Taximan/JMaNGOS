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
package org.jmangos.auth.module;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jmangos.commons.database.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Objects;

@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages = "org.jmangos.auth.dao",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManagerAuth")
@EnableTransactionManagement
public class AuthModule {

    /**
     * Database config
     */
    @Autowired
    private DatabaseConfig cfg;

    @Bean
    public DataSource dataSourceAuth() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(cfg.DB_DRIVER);
        ds.setJdbcUrl(cfg.DB_CONN_STRING);
        ds.setUsername(cfg.DB_USER);
        ds.setPassword(cfg.DB_PASS);
        return ds;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapterAuth() {
        final HibernateJpaVendorAdapter hjva = new HibernateJpaVendorAdapter();
        hjva.setShowSql(true);
        hjva.setGenerateDdl(true);
        hjva.setDatabasePlatform(this.cfg.DB_DIALECT);
        return hjva;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        final LocalContainerEntityManagerFactoryBean lcemfb =
                new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSourceAuth());
        lcemfb.setJpaVendorAdapter(jpaVendorAdapterAuth());
        lcemfb.setJpaDialect(new HibernateJpaDialect());
        return lcemfb;
    }

    @Bean
    public JpaTransactionManager transactionManagerAuth() {
        final JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(entityManagerFactory().getObject());
        return jtm;
    }

    @Bean
    public TransactionInterceptor transactionInterceptorAuth() {

        return new TransactionInterceptor(transactionManagerAuth(),
                new AnnotationTransactionAttributeSource());
    }

    @Bean
    public ActiveMQConnectionFactory ActiveMQConnectionFactory() {
        final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        return factory;
    }

}
