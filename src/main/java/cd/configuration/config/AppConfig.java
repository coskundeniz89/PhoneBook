package cd.configuration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@ComponentScan(basePackages = {"cd"},
    excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = {"cd.*"}))
@PropertySources(value = {@PropertySource("classpath:application.properties")})
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
public class AppConfig {

  /* Environmnet object. */
  @Autowired
  private Environment env;

  /* PropertySourcesPlaceholderConfigurer. */
  @Bean
  public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  /* @return  CacheManager */
  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager();
  }

  /**
   * Property dosyasından gerekli parametereleri java mail configurasyonunu ayarlar.
   * @return JavaMailSenderImpl
   */
//  @Bean
//  public JavaMailSenderImpl javaMailSenderImpl() {
//    JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
//    mailSenderImpl.setHost(env.getProperty("smtp.host"));
//    mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
//
//    mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
//    mailSenderImpl.setUsername(env.getProperty("smtp.username"));
//    mailSenderImpl.setPassword(env.getProperty("smtp.password"));
//
//    Properties javaMailProps = new Properties();
//    javaMailProps.put("mail.smtp.auth", true);
//    javaMailProps.put("mail.smtp.starttls.enable", false);
//
//    mailSenderImpl.setJavaMailProperties(javaMailProps);
//
//    return mailSenderImpl;
//  }

  /**
   * Properties to support the 'dev' mode of operation.
   */
  @Configuration
  @Profile({"default", "development"})
  @PropertySource("classpath:env.development.properties")
  static class Dev {
    // Define additional beans for this profile here

  }

  /**
   * Properties to support the 'test' mode of operation.
   */
  @Configuration
  @Profile("product")
  @PropertySource("classpath:env.product.properties")
  static class Product {
    // Define additional beans for this profile here
  }
}
