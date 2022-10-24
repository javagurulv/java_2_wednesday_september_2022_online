package lv.javaguru.java2.library.web_ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"lv.javaguru.java2.library"})
public class SpringWebConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver resourceViewResolver = new SpringResourceTemplateResolver();
		resourceViewResolver.setPrefix("classpath:/templates/");
		resourceViewResolver.setSuffix(".html");
		resourceViewResolver.setTemplateMode(TemplateMode.HTML);
		resourceViewResolver.setCharacterEncoding("UTF-8");
		resourceViewResolver.setCheckExistence(false);
		return resourceViewResolver;
	}

}