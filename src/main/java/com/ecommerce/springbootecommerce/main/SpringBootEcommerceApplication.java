package com.ecommerce.springbootecommerce.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.ecommerce.springbootecommerce.util","com.ecommerce.springbootecommerce.controller","com.ecommerce.springbootecommerce.service","com.ecommerce.springbootecommerce.filter","com.ecommerce.springbootecommerce.main"})
@EntityScan("com.ecommerce.springbootecommerce.model")
@EnableJpaRepositories("com.ecommerce.springbootecommerce.repository")
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SpringBootEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommerceApplication.class, args);
	}

}
