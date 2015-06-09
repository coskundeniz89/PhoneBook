package cd.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "cd.controller","cd.services"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  /**
   * View'a kontroller ekler.
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    super.addViewControllers(registry);
  }

  /**
   * Resources dosyalarını nerden çekileceğini belirtir.
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

  /**
   * Configure DefaultS ervlet Handling.
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  /**
   * Configure MessageSource.
   *
   * @return MessageSource
   */
  @Bean(name = "messageSource")
  public MessageSource configureMessageSource() {
    ReloadableResourceBundleMessageSource messageSource = new
        ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setCacheSeconds(5);
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  /**
   * Mapping hatalarını kontrol eder.
   *
   * @return SimpleMappingExceptionResolver
   */
  @Bean
  public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
    SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
    Properties mappings = new Properties();
    mappings.put("org.springframework.dao1.DataAccessException", "error");
    smer.setExceptionMappings(mappings);
    return smer;
  }


  /**
   * View sayfa çözümlemesi ayarları.
   *
   * @return ServletContextTemplateResolver
   */
  @Bean
  public ServletContextTemplateResolver templateResolver() {
    ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode("HTML5");
    resolver.setCharacterEncoding("UTF-8");
    resolver.setCacheable(false);
    return resolver;
  }

  /**
   * Spring template için kullanılan bean.
   *
   * @return SpringTemplateEngine
   */
  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setTemplateResolver(templateResolver());
    return engine;
  }

  /**
   * Thymeleaf view resolver yapısının ayarlamalarını yapar.
   *
   * @return ViewResolver
   */
  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setOrder(1);
    viewResolver.setViewNames(new String[]{"*"});
    viewResolver.setCache(false);
    viewResolver.setCharacterEncoding("UTF-8");
    return viewResolver;
  }

  /**
   * Interceptors eklediğimiz kısımdır.
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    HandlerInterceptorAdapter handlerInterceptorAdapter = new HandlerInterceptorAdapter() {

      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                               Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return true;
      }
    };
    registry.addInterceptor(handlerInterceptorAdapter);

    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    registry.addInterceptor(localeChangeInterceptor);
  }

  /**
   * Cookie'de tutulan verileri ayarlar.
   *
   * @return LocaleResolver
   */
  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
    cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en"));
    return cookieLocaleResolver;
  }
}