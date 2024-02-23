package com.springConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

public class SpringConfiguration {
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public SpringResourceTemplateResolver templateResolver() {

	SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

	resolver.setPrefix("/WEB-INF/views/");

	resolver.setSuffix(".html");

	resolver.setApplicationContext(applicationContext);

	resolver.setTemplateMode(TemplateMode.HTML);
	return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
	//load the file found by the Resolver
	SpringTemplateEngine engine = new SpringTemplateEngine();
	//mapping the engine to the resolver
	engine.setTemplateResolver(templateResolver());
	//for accepting the Expression Language eg: ${temo.name} ${message}
	engine.setEnableSpringELCompiler(true);
	return engine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
	//thyme leaf object
	ThymeleafViewResolver vr = new ThymeleafViewResolver();
	//loader - engine
	vr.setTemplateEngine(templateEngine());
	return vr;
	}
	}


