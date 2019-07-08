package com.adobe.prj.cfg;
 
import java.util.Properties;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Configuration
@EnableTransactionManagement		// Tells Spring to use the distributed transaction management.
public class AppConfig {
       /*
       * Create a pool of database connections
       * Prefer c3p0 or Hikari datasource connections
       */
      
       @Bean
       public DriverManagerDataSource getDataSource() {
              DriverManagerDataSource ds = new DriverManagerDataSource();
              ds.setDriverClassName("com.mysql.jdbc.Driver");
              ds.setUrl("jdbc:mysql://localhost:3306/bootcamp");
              ds.setUsername("root");
              ds.setPassword("123456789");
              return ds;
       }
      
       @Bean
       public LocalContainerEntityManagerFactoryBean getEMF() {
              LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
              emf.setDataSource(getDataSource());
              emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
              emf.setPackagesToScan("com.adobe.prj.entity");
             
              Properties props = new Properties();
              props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
              props.setProperty("hibernate.hbm2ddl.auto", "update");
              emf.setJpaProperties(props);
              return emf;
       }
      
       @Bean
       public PlatformTransactionManager getTransaction() {
              return new JpaTransactionManager();
       }
 
}