package vn.iotstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import vn.iotstar.config.MySiteMeshFilter;


@SpringBootApplication
public class DemoSpringBoot3Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBoot3Application.class, args);
	}

    @Bean
    FilterRegistrationBean<MySiteMeshFilter> siteMeshFilter(){
		FilterRegistrationBean<MySiteMeshFilter> filterRegistrationBean 
				= new FilterRegistrationBean<MySiteMeshFilter>();
		filterRegistrationBean.setFilter(new MySiteMeshFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
}
