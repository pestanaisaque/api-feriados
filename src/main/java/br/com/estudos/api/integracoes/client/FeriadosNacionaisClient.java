package br.com.estudos.api.integracoes.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://brasilapi.com.br/api/feriados/v1", name = "feriadosNacionais")
public interface FeriadosNacionaisClient {
	
	@GetMapping("/{ano}")
	List<FeriadosNacionais> getFeriadosDoAno(@PathVariable("ano") int ano);
}
