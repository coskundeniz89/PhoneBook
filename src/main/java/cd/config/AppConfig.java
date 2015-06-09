package cd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;


@Configuration
@ComponentScan(basePackages = { "cd.controller","cd.interfaces","cd.config","cd.entities" })
@PropertySources(value = {@PropertySource("classpath:application.properties")})
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
//  @Bean
//  public CacheManager cacheManager() {
//    return new ConcurrentMapCacheManager();
//  }
}
