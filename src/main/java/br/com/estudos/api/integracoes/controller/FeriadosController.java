package br.com.estudos.api.integracoes.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.api.integracoes.domain.Feriado;
import br.com.estudos.api.integracoes.domain.FeriadosRequest;
import br.com.estudos.api.integracoes.service.FeriadosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class FeriadosController {

	@Autowired
	private FeriadosService service;

	@Operation(summary = "Salva uma nova lista de feriados de acordo com o ano passado por parâmetro", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Salvo com sucesso"),
			@ApiResponse(responseCode = "422", description = "Feriados já cadastrados")
	})
	@PostMapping("/feriados")
	public ResponseEntity<Feriado> save(@RequestBody FeriadosRequest request) {
		var feriado = service.createFeriado(request);
		return ResponseEntity.created(URI.create(String.format("/feriados/%s", feriado.getAno()))).body(feriado);
	}
	

	@Operation(summary = "Busca todos os anos e seus respectivos feriados", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum feriado encontrado")
	})
	@GetMapping("/feriados")
	public ResponseEntity<List<Feriado>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	

	@Operation(summary = "Busca uma lista de feriados de um determinado ano", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum feriado encontrado para o ano informado")
	})
	@GetMapping("/feriados/{ano}")
	public ResponseEntity<Feriado> getByYear(@PathVariable String ano) {
		return ResponseEntity.ok(service.getByYear(ano));
	}

}
