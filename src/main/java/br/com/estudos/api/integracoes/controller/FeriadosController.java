package br.com.estudos.api.integracoes.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.api.integracoes.domain.FeriadosRequest;
import br.com.estudos.api.integracoes.domain.Feriado;
import br.com.estudos.api.integracoes.service.FeriadosService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class FeriadosController {

	private final FeriadosService service;

	@PostMapping("/feriados")
	public ResponseEntity<Feriado> save(@RequestBody FeriadosRequest request) {
		var feriado = service.createFeriado(request);
		return ResponseEntity.created(URI.create(String.format("/feriados/%s", feriado.getAno())))
				.body(feriado);
	}

	@GetMapping("/feriados")
	public ResponseEntity<List<Feriado>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/feriados/{ano}")
	public ResponseEntity<Feriado> getByYear(@PathVariable String ano) {
		return ResponseEntity.ok(service.getByYear(ano));
	}

}
