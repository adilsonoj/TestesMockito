package br.alunos.calcula;

import java.util.ArrayList;
import java.util.List;

import br.alunos.dao.AlunoDAO;
import br.alunos.dao.AlunoDAOFalso;
import br.alunos.modelo.Aluno;

public class Avaliacao {
	private final AlunoDAO dao;
	private List<Aluno> aprovados = new ArrayList<Aluno>();
	private Aluno aluno=null;
	
	
	public Avaliacao(AlunoDAO dao) {
		this.dao = dao;
	}
	
	public Avaliacao() {
		this.dao = new AlunoDAO();
	}

	public Boolean avaliar(Aluno aluno){
		
		if(aluno.getNota() >= 7) 
			return true;
		else 
			return false;
	}
	
	
	
	//avalia lista de alunos com DAO falso
		public void avaliarListaComDaoFalso(){
			AlunoDAOFalso avaliacaoDaoFalso = new AlunoDAOFalso();
			
			for(Aluno aluno : avaliacaoDaoFalso.alunos){
				if(aluno.getNota() >= 7) 
					this.aprovados.add(aluno);
				
			}
		}	
			
	//avalia lista de alunos com dao real
	//criar Construtor para receber o dao falso na hora do teste
	public void avaliarLista(){
		
		for(Aluno aluno : dao.busca()){
			if(aluno.getNota() >= 7) {
				this.aprovados.add(aluno);
				aluno.setAprovado(true);
				dao.atualiza(aluno);
				
			}
		}
		
	}
	
public void avaliarListaExcecao(){
		
		for(Aluno aluno : dao.busca()){
//			try{
			if(aluno.getNota() >= 7) {
				this.aprovados.add(aluno);
				aluno.setAprovado(true);
				dao.atualiza(aluno);
				
			}
//			}catch(Exception e){
//				//
//			}
		}
		
	}

	
	
	public List<Aluno> getAprovados() {
		return aprovados;
	}

	public void setAprovados(List<Aluno> aprovados) {
		this.aprovados = aprovados;
	}


	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
}
