package br.com.estudos.api.integracoes.exception.handler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String mensagem;
	private LocalDateTime data;
	private int status;
	private String path;
	
	public ErrorResponse(String mensagem, LocalDateTime data, int status, String path) {
		super();
		this.mensagem = mensagem;
		this.data = data;
		this.status = status;
		this.path = path;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
