package cd.configuration.persistence;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by coskun.deniz on 04.06.2015.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "cd.entities")
public class PersistenceContext {

  @Autowired
  private Environment env;

  @Bean
  //@Bean(destroyMethod = "close")
  public DataSource dataSource() {
    BasicDataSource basicDataSource = new BasicDataSource();

    basicDataSource.setDriverClassName(env.getProperty("db.driver"));
    basicDataSource.setUrl(env.getProperty("db.url"));
    basicDataSource.setUsername(env.getProperty("db.username"));
    basicDataSource.setPassword(env.getProperty("db.password"));

    return basicDataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());


    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(Boolean.TRUE);
    vendorAdapter.setShowSql(Boolean.TRUE);
    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

    entityManagerFactoryBean.setPackagesToScan("cd.entities");

    Properties jpaProperties = new Properties();

    jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    jpaProperties.put("hibernate.ejb.naming_strategy", env.getProperty("hibernate.ejb.naming_strategy"));
    jpaProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
    jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

    entityManagerFactoryBean.setJpaProperties(jpaProperties);

    entityManagerFactoryBean.afterPropertiesSet();
    entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    return entityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager jpaTransactionManager() {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return jpaTransactionManager;
  }


}
