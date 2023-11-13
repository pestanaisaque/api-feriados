package br.com.estudos.api.integracoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudos.api.integracoes.client.FeriadosNacionais;
import br.com.estudos.api.integracoes.client.FeriadosNacionaisClient;
import br.com.estudos.api.integracoes.domain.Feriado;
import br.com.estudos.api.integracoes.domain.FeriadosRequest;
import br.com.estudos.api.integracoes.exception.NotFoundException;
import br.com.estudos.api.integracoes.exception.UnprocessableEntityException;
import br.com.estudos.api.integracoes.repository.FeriadosRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FeriadosServiceImpl implements FeriadosService {
	
	@Autowired
	private FeriadosRepository repository;
	
	@Autowired
	private FeriadosNacionaisClient feriadosNacionaisClient;

	@Override
	public Feriado createFeriado(FeriadosRequest request) {
		String ano = request.getAno();
		Feriado existingFeriado = repository.getByYear(ano);
		
		if(existingFeriado == null) {
			int intAno = Integer.parseInt(ano);
			List<FeriadosNacionais> feriadosNacionais = feriadosNacionaisClient.getFeriadosDoAno(intAno);
			
			existingFeriado = new Feriado(request);
			existingFeriado.setFeriadosNacionais(feriadosNacionais);
			
			return repository.save(existingFeriado);
			
		} else {
			throw new UnprocessableEntityException("Os feriados para o ano informado já estão cadastrados");
		}
	}

	@Override
	public Feriado getByYear(String name) {
		var existingFeriado = repository.getByYear(name);
		
		if(existingFeriado != null) {			
			return existingFeriado;
		} else {
			throw new NotFoundException("Os feriados para o ano informado não se encontram cadastrados, favor inserir o mesmo e tentar novamente");		
		}
	}

	@Override
	public List<Feriado> getAll() {
		return repository.getAll();
	}

}
