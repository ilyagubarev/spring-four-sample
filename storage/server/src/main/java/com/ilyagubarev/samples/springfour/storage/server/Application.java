package com.ilyagubarev.samples.springfour.storage.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Application implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // NOTE: root application context
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setParent(null);
        applicationContext.register(ApplicationConfiguration.class);
        applicationContext.refresh();
        // TODO: consider setting servlet context
        // TODO: consider creating servlet context listener

        // NOTE: models dispatcher servlet
        AnnotationConfigWebApplicationContext modelDispatcherContext = new AnnotationConfigWebApplicationContext();
        modelDispatcherContext.setParent(applicationContext);
        modelDispatcherContext.setServletContext(servletContext);
        modelDispatcherContext.register(ModelDispatcherConfiguration.class);
        modelDispatcherContext.refresh();
        DispatcherServlet modelDispatcherServlet = new DispatcherServlet(modelDispatcherContext);
        ServletRegistration.Dynamic modelDispatcherDynamic = servletContext.addServlet("modelDispatcher", modelDispatcherServlet);
        modelDispatcherDynamic.addMapping("/resources/models/*");
        modelDispatcherDynamic.setLoadOnStartup(1);

        // NOTE: views dispatcher servlet
        AnnotationConfigWebApplicationContext viewDispatcherContext = new AnnotationConfigWebApplicationContext();
        viewDispatcherContext.setParent(applicationContext);
        viewDispatcherContext.setServletContext(servletContext);
        viewDispatcherContext.register(ViewDispatcherConfiguration.class);
        viewDispatcherContext.refresh();
        DispatcherServlet viewDispatcherServlet = new DispatcherServlet(viewDispatcherContext);
        ServletRegistration.Dynamic viewDispatcherDynamic = servletContext.addServlet("viewDispatcher", viewDispatcherServlet);
        viewDispatcherDynamic.addMapping("/resources/views/*");
        viewDispatcherDynamic.setLoadOnStartup(1);
    }
}
