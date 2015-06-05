package cd.configuration.init;

import cd.configuration.config.AppConfig;
import cd.configuration.config.WebMvcConfig;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * Context okurken hangi  AppConfig  dosyasını okumamızı sağlar.
   * @return Class $lt; ? &gt; []
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{AppConfig.class};
  }

  /**
   * Context okurken hangi  WebMvcConfig dosyasını okumamızı sağlar.
   * @return  Class $lt; ? &gt; []
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[]{WebMvcConfig.class};
  }

  /**
   * get Servlet Mappings.
   * @return String[]
   */
  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  /**
   * Spring security tanımlar.
   * @return Filter[]
   */
  @Override
  protected Filter[] getServletFilters() {
    return new Filter[]{
        new DelegatingFilterProxy("springSecurityFilterChain"),
        new OpenEntityManagerInViewFilter()};
  }
}