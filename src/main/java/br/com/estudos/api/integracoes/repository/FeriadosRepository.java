package br.com.estudos.api.integracoes.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.estudos.api.integracoes.domain.Feriado;

@Component
public class FeriadosRepository {
	private Map<String, Feriado> feriados = new HashMap<>();

	public Feriado save(Feriado feriado) {
		var id = UUID.randomUUID();
		feriados.put(id.toString(), feriado);
		return feriados.get(id.toString());
	}

	public Feriado getByYear(String year) {
		Optional<Feriado> feriadoEncontrado = feriados.values().stream().filter(feriado -> year.equals(feriado.getAno())).findFirst();
		return feriadoEncontrado.orElse(null);
	}

	public List<Feriado> getAll() {
		return feriados.values().stream().collect(Collectors.toList());
	}
}
