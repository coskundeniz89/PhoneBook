package cd.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"cd.model"})
//@EnableJpaRepositories(basePackages = {"cd.repositories"})
public class PersistenceConfig {

  @Autowired
  private Environment env;

  @Bean
  JpaTransactionManager jpaTransactionManager() {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return jpaTransactionManager;
  }

  private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
  private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "spring.jpa.hibernate.naming_strategy";

  /**
   * Database türünü ve model classların olduğu paketleri ayarlar.
   *
   * @return LocalContainerEntityManagerFactoryBean
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();


    factory.setDataSource(dataSource());
    factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    factory.setPackagesToScan("cd.model");

    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, env.getProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
    jpaProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
    jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    jpaProperties.put("hibernate.default_schema", env.getProperty("hibernate.default_schema"));
    factory.setJpaProperties(jpaProperties);

//    factory.afterPropertiesSet();
//    factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    return factory;
  }

  /**
   * Hibernate Exception Translator.
   *
   * @return HibernateExceptionTranslator
   */
  @Bean
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }

  /**
   * Database konfigürasyonunu belirtir.
   *
   * @return DataSource
   */
  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(env.getProperty("db.driverClassName"));
    dataSource.setUrl(env.getProperty("db.url"));
    dataSource.setUsername(env.getProperty("db.username"));
    dataSource.setPassword(env.getProperty("db.password"));
    return dataSource;
  }

}
