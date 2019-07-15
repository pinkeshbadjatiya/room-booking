package com.adobe.prj.cfg;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration	 
@EnableWebMvc
@ComponentScan({ "com.adobe" })
public class WebConfig extends WebMvcConfigurerAdapter {
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
//	}
}