package ru.job4j.spring.police.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Class WebInit.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 22.06.2020
 */
public class WebInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(WebConfig.class, DataConfig.class);
        rootContext.refresh();

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(Boolean.TRUE);
        FilterRegistration.Dynamic encoding = servletContext.addFilter("encoding", filter);
        encoding.addMappingForUrlPatterns(null, Boolean.FALSE, "/*");

        DispatcherServlet servlet = new DispatcherServlet(rootContext);
        ServletRegistration.Dynamic reg =
                servletContext.addServlet("app", servlet);
        reg.setLoadOnStartup(1);
        reg.addMapping("/");
    }
}
