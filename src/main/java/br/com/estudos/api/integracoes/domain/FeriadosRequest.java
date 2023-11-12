package br.com.estudos.api.integracoes.domain;

import lombok.Data;

@Data
public class FeriadosRequest {
	
	private String ano;

	
	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
}
