package cd.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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

}