package br.com.estudos.api.integracoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.estudos.api.integracoes.domain.Feriado;
import br.com.estudos.api.integracoes.domain.FeriadosRequest;
import br.com.estudos.api.integracoes.repository.FeriadosRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FeriadosServiceImpl implements FeriadosService {

	private final FeriadosRepository repository;

	@Override
	public Feriado createFeriado(FeriadosRequest request) {
		return repository.save(new Feriado(request));
	}

	@Override
	public Feriado getByYear(String name) {
		return repository.getByYear(name);
	}

	@Override
	public List<Feriado> getAll() {
		return repository.getAll();
	}

}
