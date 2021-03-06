package com.ilyagubarev.samples.springfour.storage.server;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.ilyagubarev.samples.springfour.storage.server.context.LogAspectBean;
import com.ilyagubarev.samples.springfour.storage.server.repositories.Bag;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories("com.ilyagubarev.samples.springfour.storage.server.repositories.auto")
public abstract class ApplicationConfiguration {

    @Bean
    public LogAspectBean logAspect() {
        return new LogAspectBean();
    }

    @Bean
    @Profile({"dev-alpha", "dev-beta"})
    public EmbeddedDatabaseBuilder developmentDataSourceBuilder() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.DERBY)
                .addScript("classpath:dev-schema-initialization.sql")
                .addScript("classpath:dev-schema-population.sql");
    }

    @Bean
    @Profile({"dev-alpha"})
    public DataSource alphaDataSource(EmbeddedDatabaseBuilder builder) {
        return builder.build();
    }

    @Bean
    @Profile({"dev-beta"})
    public DataSource betaDataSource(EmbeddedDatabaseBuilder builder) {
        return builder.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan(Bag.class.getPackage().getName());
        return emfb;
    }
    @Bean
    public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public JpaTransactionManager transactionManager(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource, jpaVendorAdapter).getObject());
        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.DERBY);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }
}
