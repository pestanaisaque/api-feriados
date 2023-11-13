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
	
}
