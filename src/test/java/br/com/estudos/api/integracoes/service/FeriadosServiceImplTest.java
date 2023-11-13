package br.com.estudos.api.integracoes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.estudos.api.integracoes.client.FeriadosNacionais;
import br.com.estudos.api.integracoes.client.FeriadosNacionaisClient;
import br.com.estudos.api.integracoes.domain.Feriado;
import br.com.estudos.api.integracoes.domain.FeriadosRequest;
import br.com.estudos.api.integracoes.exception.NotFoundException;
import br.com.estudos.api.integracoes.exception.UnprocessableEntityException;
import br.com.estudos.api.integracoes.repository.FeriadosRepository;

@ExtendWith(MockitoExtension.class)
public class FeriadosServiceImplTest {
	
	@InjectMocks
	private FeriadosServiceImpl feriadosServiceImpl;
	
	@Mock
	private FeriadosRepository feriadosRepository;
	
	@Mock
	private FeriadosNacionaisClient feriadosNacionaisClient;
	
	
	private FeriadosRequest feriadosRequest;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		feriadosRequest = new FeriadosRequest();
		feriadosRequest.setAno("2023");
	}
	
	@Test
	public void deveCriarUmNovoFeriadosDoAnoInformadoNoRequestSeNãoExistir(){
		when(feriadosRepository.getByYear("2023")).thenReturn(null);
		
		List<FeriadosNacionais> feriadosNacionais = Arrays.asList(new FeriadosNacionais("2023-01-01", "Ano Novo", "Nacional"), new FeriadosNacionais("2023-04-21", "Tiradentes", "Nacional"));
        when(feriadosNacionaisClient.getFeriadosDoAno(2023)).thenReturn(feriadosNacionais);
        
        Feriado novoFeriadoCriado = new Feriado(feriadosRequest);
        novoFeriadoCriado.setFeriadosNacionais(feriadosNacionais);
        when(feriadosRepository.save(Mockito.any(Feriado.class))).thenReturn(novoFeriadoCriado);
        
        Feriado novoFeriadoReturn = feriadosServiceImpl.createFeriado(feriadosRequest);

        assertNotNull(novoFeriadoReturn);
        assertEquals("2023", novoFeriadoReturn.getAno());
        assertEquals(feriadosNacionais, novoFeriadoReturn.getFeriadosNacionais());
	}
	
	
	@Test
	public void deveRetornarUmaExceptionCasoJaExistaUmFeriadoDeAcordoComAnoInformado(){
		Feriado existingFeriado = new Feriado(feriadosRequest);
		when(feriadosRepository.getByYear(existingFeriado.getAno())).thenReturn(existingFeriado);

		Exception thrown = assertThrows(UnprocessableEntityException.class, () -> feriadosServiceImpl.createFeriado(feriadosRequest));
		assertEquals("Os feriados para o ano informado já estão cadastrados", thrown.getMessage());
	}
	
	
	@Test
	public void deveRetornarUmaListaDeFeriadosExistentesDeAcordoComAnoInformado(){
		Feriado existingFeriado = new Feriado(feriadosRequest);
		when(feriadosRepository.getByYear(existingFeriado.getAno())).thenReturn(existingFeriado);
		
		Feriado existingFeriadosReturn = feriadosServiceImpl.getByYear(existingFeriado.getAno());
		
		assertNotNull(existingFeriadosReturn);
		assertEquals(existingFeriado.getAno(), existingFeriadosReturn.getAno());
	}
	
	
	@Test
	public void deveRetornarUmaExceptionCasoNãoExistaUmaListaDeFeriadosDeAcordoComAnoInformado(){
		when(feriadosRepository.getByYear("2023")).thenReturn(null);
		
		Exception thrown = assertThrows(NotFoundException.class, () -> feriadosServiceImpl.getByYear("2023"));
		assertEquals("Os feriados para o ano informado não se encontram cadastrados, favor inserir o mesmo e tentar novamente", thrown.getMessage());
	}
	
	
	@Test
	public void deveRetornarUmaListaDeTodosOsFeriadosExistentes(){
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
        when(feriadosRepository.getAll()).thenReturn(feriadosList);
        
        List<Feriado> feriadosListReturn = feriadosServiceImpl.getAll();

        assertNotNull(feriadosListReturn);
        assertEquals(2, feriadosListReturn.size());
	}
	
}
