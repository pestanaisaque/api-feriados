package br.com.estudos.api.integracoes.domain;

import java.util.List;

import br.com.estudos.api.integracoes.client.FeriadosNacionais;
import lombok.Data;

@Data
public class Feriado {

	private String ano;
	private List<FeriadosNacionais> feriadosNacionais;

	public Feriado(FeriadosRequest request) {
		this.ano = request.getAno();
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public List<FeriadosNacionais> getFeriadosNacionais() {
		return feriadosNacionais;
	}

	public void setFeriadosNacionais(List<FeriadosNacionais> feriadosNacionais) {
		this.feriadosNacionais = feriadosNacionais;
	}
	
}
