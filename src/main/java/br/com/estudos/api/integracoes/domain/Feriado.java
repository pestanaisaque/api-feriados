package br.com.estudos.api.integracoes.domain;

import java.util.List;

import br.com.estudos.api.integracoes.client.FeriadosNacionais;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Feriado {

	private String ano;
	private List<FeriadosNacionais> feriadosNacionais;

	public Feriado(FeriadosRequest request) {
		this.ano = request.getAno();
	}
	
}
