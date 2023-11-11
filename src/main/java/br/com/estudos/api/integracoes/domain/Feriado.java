package br.com.estudos.api.integracoes.domain;

import lombok.Data;

@Data
public class Feriado {

	private String ano;

	public Feriado(FeriadosRequest request) {
		this.ano = request.getAno();
	}
}
