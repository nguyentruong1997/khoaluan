package com.migi.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(HelloWorldConfiguration.class);
		ctx.setServletContext(servletContext);
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);  //url sai throw ra loi NohandlerException -> Exception Handler
		
		ServletRegistration.Dynamic servlet  = servletContext.addServlet("dispatcher",dispatcherServlet);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
		CharacterEncodingFilter  characterEncodingFilter = new CharacterEncodingFilter("UTF-8",true);
		characterEncodingFilter.setForceEncoding(true); 
		servletContext.addFilter("encodingFilter", characterEncodingFilter)
		.addMappingForUrlPatterns( null, false, "/*");
		
	}

}
