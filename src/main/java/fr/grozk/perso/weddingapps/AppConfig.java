package fr.grozk.perso.weddingapps;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
	 
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("ressources/**/**",
					"/img/**",
	                "/css/**",
	                "/js/**",
	                "/i18n/**")
	                        .addResourceLocations("/resources/",
	                        		"classpath:/static/img/",
	                        		"classpath:/static/css/",
	                                "classpath:/static/js/",
	                                "classpath:/i18n/");
		}


	@Bean
	public DataSource dataSource() throws URISyntaxException {
		URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.jdbc");
		dataSourceBuilder.url(dbUrl);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		return dataSourceBuilder.build();
	}

		@Bean
		public InternalResourceViewResolver viewResolver() {
			InternalResourceViewResolver viewResolver 
	                         = new InternalResourceViewResolver();
			viewResolver.setPrefix("/resources/templates/");
			viewResolver.setSuffix(".html");
			return viewResolver;
		}
		
		  @Bean("messageSource")
		   public MessageSource messageSource() {
		      ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
		      messageSource.setBasename("classpath:i18n/messages");
		      messageSource.setDefaultEncoding("UTF-8");
		      messageSource.setUseCodeAsDefaultMessage(true);
		      return messageSource;
		   }
		  
		  @Bean
		   public LocaleResolver localeResolver() {
		      CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		      return localeResolver;
		   }
		  

		   @Override
		   public void addInterceptors(InterceptorRegistry registry) {
		      LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		      localeChangeInterceptor.setParamName("lang");
		      registry.addInterceptor(localeChangeInterceptor);
		   }
}
