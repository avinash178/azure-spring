package com.eom;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VodaEomSampleApplication extends SpringBootServletInitializer{

	 //private static final Logger log = LoggerFactory.getLogger(VodaEomSampleApplication.class);
	 public static void main(String[] args) {
			SpringApplication.run(VodaEomSampleApplication.class, args);

		}
	 
	 
		/*
		 * // this is for create war file and run it on external tomcat
		 * 
		 * @Override protected SpringApplicationBuilder
		 * configure(SpringApplicationBuilder builder) { return
		 * builder.sources(VodaEomSampleApplication.class); }
		 */


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
	
	

}
