package br.alunos.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;
import java.util.Arrays;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.junit.Test;

import br.alunos.calcula.Avaliacao;
import br.alunos.dao.AlunoDAO;
import br.alunos.dao.AlunoDAOFalso;
import br.alunos.modelo.Aluno;

public class AvaliacaoDAOTest {
	
	@Test
	public void AlunoAprovadoJunit(){
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno();
		aluno1.setNome("João");
		aluno1.setNota(7);
		
		aluno2.setNome("Maria");
		aluno2.setNota(8);

		//Dao falso
		AlunoDAOFalso daoFalso = new AlunoDAOFalso();
		daoFalso.salva(aluno1);
		daoFalso.salva(aluno2);
		
		Avaliacao avalia = new Avaliacao();
		avalia.avaliarListaComDaoFalso();
		assertEquals(2, avalia.getAprovados().size());
		assertEquals("Maria", avalia.getAprovados().get(1).getNome());
		
	}
	
	@Test
	public void AlunoAprovadoMockito(){
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno();
		aluno1.setNome("João");
		aluno1.setNota(7);
		aluno2.setNome("Maria");
		aluno2.setNota(8);
		
		List<Aluno> alunosAprovados = Arrays.asList(aluno1, aluno2);
		//Dao falso
		AlunoDAO daoFalso = mock(AlunoDAO.class);
		//ensina ao metodo o que deve retornar
		when(daoFalso.busca()).thenReturn(alunosAprovados);
		
		
		Avaliacao avaliacao = new Avaliacao(daoFalso);
		avaliacao.avaliarLista();
		
		assertEquals(2, avaliacao.getAprovados().size());
		assertEquals("Maria", avaliacao.getAprovados().get(1).getNome());
		
		
	}
	
	@Test
	public void deveAtualizarAluno(){
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno();
		aluno1.setNome("João");
		aluno1.setNota(7);
		aluno2.setNome("Maria");
		aluno2.setNota(8);
		
		List<Aluno> alunosAprovados = Arrays.asList(aluno1, aluno2);
		//Dao falso
		AlunoDAO daoFalso = mock(AlunoDAO.class);
		//ensina ao metodo o que deve retornar
		when(daoFalso.busca()).thenReturn(alunosAprovados);
		
		
		Avaliacao avaliacao = new Avaliacao(daoFalso);
		avaliacao.avaliarLista();
		
		//verify(daoFalso).atualiza(aluno1);
		verify(daoFalso,times(1)).atualiza(aluno2);
		
	}
	
	
	@Test
	public void deveLancarExcecao(){
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno();
		aluno1.setNome("João");
		aluno1.setNota(7);
		aluno2.setNome("Maria");
		aluno2.setNota(8);
		
		List<Aluno> alunosAprovados = Arrays.asList(aluno1, aluno2);
		//Dao falso
		AlunoDAO daoFalso = mock(AlunoDAO.class);
		//ensina ao metodo o que deve retornar
		when(daoFalso.busca()).thenReturn(alunosAprovados);
		
		//força a execução uma exceção
		doThrow(new RuntimeException()).when(daoFalso).atualiza(aluno1);

		Avaliacao avaliacao = new Avaliacao(daoFalso);
		avaliacao.avaliarListaExcecao();
		
		
		verify(daoFalso).atualiza(aluno2);
		
	}
}
