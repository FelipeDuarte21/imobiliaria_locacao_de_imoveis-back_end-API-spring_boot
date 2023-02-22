package br.com.luizfelipeduarte.imobiliariaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ImobiliariaApiApplication{

	public static void main(String[] args) {
		
		SpringApplication.run(ImobiliariaApiApplication.class, args);
		
	}
	
	
}
