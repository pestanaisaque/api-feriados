package br.com.estudos.api.integracoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFeignClients(basePackages = "br.com.estudos.api.integracoes.client")
@SpringBootApplication
@EnableFeignClients
public class ApiIntegracoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiIntegracoesApplication.class, args);
	}

}