package br.com.estudos.api.integracoes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.com.estudos.api.integracoes.client.FeriadosNacionais;
import br.com.estudos.api.integracoes.domain.Feriado;
import br.com.estudos.api.integracoes.domain.FeriadosRequest;
import br.com.estudos.api.integracoes.service.FeriadosService;

@ExtendWith(MockitoExtension.class)
public class FeriadosControllerTest {

	@InjectMocks
	private FeriadosController feriadosController;
	
	@Mock
	private FeriadosService feriadosService;
	
	
	private FeriadosRequest feriadosRequest;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		feriadosRequest = new FeriadosRequest();
		feriadosRequest.setAno("2023");
	}
	
	
	@Test
	public void deveSalvarUmNovoFeriado(){
        Feriado novoFeriado = new Feriado(feriadosRequest);
        when(feriadosService.createFeriado(feriadosRequest)).thenReturn(novoFeriado);
         
        ResponseEntity<Feriado> responseEntity = feriadosController.save(feriadosRequest); 
        
        assertNotNull(responseEntity);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(novoFeriado, responseEntity.getBody());
	}
	
	
	@Test
	public void deveRetornarTodosOsAnosComSeusRespectivosFeriados(){
		List<FeriadosNacionais> feriadosNacionais1 = Arrays.asList(new FeriadosNacionais("2022-01-01", "Ano Novo", "Nacional"), new FeriadosNacionais("2022-04-21", "Tiradentes", "Nacional"));
		List<FeriadosNacionais> feriadosNacionais2 = Arrays.asList(new FeriadosNacionais("2023-01-01", "Ano Novo", "Nacional"), new FeriadosNacionais("2023-04-21", "Tiradentes", "Nacional"));
		
		FeriadosRequest feriadoRequest1 = new FeriadosRequest();
		feriadoRequest1.setAno("2022");
		FeriadosRequest feriadoRequest2 = new FeriadosRequest();
		feriadoRequest2.setAno("2023");
		
		Feriado feriado1 = new Feriado(feriadoRequest1);
		feriado1.setFeriadosNacionais(feriadosNacionais1);
		
		Feriado feriado2 = new Feriado(feriadoRequest2);
		feriado2.setFeriadosNacionais(feriadosNacionais2);
		
		List<Feriado> feriadosList = Arrays.asList(feriado1, feriado2);
		when(feriadosService.getAll()).thenReturn(feriadosList);
		
		ResponseEntity<List<Feriado>> responseEntity = feriadosController.getAll();
		
		assertEquals(200, responseEntity.getStatusCodeValue());
	    assertEquals(feriadosList, responseEntity.getBody());
	}
	
	
	@Test
	public void deveRetornarUmFeriadoDeAcordoComAnoInformado(){
        Feriado feriado = new Feriado(feriadosRequest);
        when(feriadosService.getByYear(feriado.getAno())).thenReturn(feriado);
        
        ResponseEntity<Feriado> responseEntity = feriadosController.getByYear(feriado.getAno());
        
        assertEquals(200, responseEntity.getStatusCodeValue());
	    assertEquals(feriado, responseEntity.getBody());
	}
	
}
