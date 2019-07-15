package com.adobe.prj.cfg;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
//		servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");
		WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		System.out.println("Add mappings >> ");
		dispatcher.addMapping("/api/*");
	}

	private WebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.adobe");
		return context;
	}
}



//package com.adobe.prj.cfg;
//
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
// 
//   @Override
//   protected Class<?>[] getRootConfigClasses() {
//      return new Class[] { SecurityConfig.class };
//   }
// 
//   @Override
//   protected Class<?>[] getServletConfigClasses() {
//      return new Class[] { WebConfig.class };
//   }
// 
//   @Override
//   protected String[] getServletMappings() {
//      return new String[] { "/" };
//   }
//}
