package com.mycompany.grabberrasskazov;

import com.mycompany.grabberrasskazov.beans.MainBean;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        try {
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUsername("root");
            ds.setPassword("root");
            String u = "jdbc:mysql://localhost:3306/arthostingpanel?characterEncoding=utf-8";
            ds.setUrl(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;

    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        String mappingResources[] = new String[5];
        mappingResources[0] = ("orm/StoryWriters.hbm.xml");
        mappingResources[1] = ("orm/StoriesToWritersRelations.hbm.xml");
        mappingResources[2] = ("orm/StoryCategories.hbm.xml");
        mappingResources[3] = ("orm/StoriesToCategoriessRelations.hbm.xml");
        mappingResources[4] = ("orm/Stories.hbm.xml");
        factoryBean.setMappingResources(mappingResources);

//        SET FOREIGN_KEY_CHECKS = 0; 
//TRUNCATE `grabberRasskazov`.`rasskazi`; 
//TRUNCATE `grabberRasskazov`.`catsRelations`; 
//SET FOREIGN_KEY_CHECKS = 1;
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.format_sql", "false");
        props.setProperty("hibernate.show_sql", "false");
//        props.setProperty("hibernate.format_sql", "true");
//        props.setProperty("hibernate.show_sql", "true");

        factoryBean.setHibernateProperties(props);
        factoryBean.setPackagesToScan("com.mycompany.grabberrasskazov.models");

        return factoryBean;
    }

    @Bean
    public MainBean mainBean() {
        return new MainBean();
    }
}
