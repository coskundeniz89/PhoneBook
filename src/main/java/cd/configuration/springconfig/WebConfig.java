package cd.configuration.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Created by coskun.deniz on 13.05.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cd.controller")
public class WebConfig {

    @Bean
    public TemplateResolver templateResolver() {
        //System.out.println("templateResolver");
        ServletContextTemplateResolver resolver =
                new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        //System.out.println("templateEngine");

        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver());

        return springTemplateEngine;
    }

    @Bean
    public ViewResolver thymeleafViewResolver() {
        //System.out.println("thymeleafViewResolver");

        ThymeleafViewResolver springThymeleafViewResolver = new ThymeleafViewResolver();
        springThymeleafViewResolver.setTemplateEngine(templateEngine());
        springThymeleafViewResolver.setOrder(1);
        return springThymeleafViewResolver;
    }

}