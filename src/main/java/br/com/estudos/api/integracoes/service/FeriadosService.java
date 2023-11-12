package br.com.estudos.api.integracoes.service;

import java.util.List;

import br.com.estudos.api.integracoes.domain.Feriado;
import br.com.estudos.api.integracoes.domain.FeriadosRequest;

public interface FeriadosService {
	
	Feriado createFeriado(FeriadosRequest request);

	Feriado getByYear(String name);

	List<Feriado> getAll();
}
