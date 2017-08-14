package br.alunos.test;

import org.junit.Test;

import br.alunos.calcula.Avaliacao;
import br.alunos.modelo.Aluno;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class AvaliacaoTest {

	@Test
	public void alunoAprovadoJunit(){
		Aluno aluno = new Aluno();
		aluno.setNome("José");
		aluno.setNota(7);
		
		Avaliacao avaliacao = new Avaliacao();
		assertTrue(avaliacao.avaliar(aluno));
		
	}
	
	@Test
	public void alunoAprovadoMock(){
		//Aluno aluno = new Aluno();
		Avaliacao avaliacao = new Avaliacao();
		
		Aluno aluno = mock(Aluno.class);
		when(aluno.getNota()).thenReturn(8);
		
		assertTrue(avaliacao.avaliar(aluno));
		
	}
	
	
}
