package br.alunos.dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.alunos.modelo.Aluno;




public class AlunoDAO {

	
	public List<Aluno> busca(){
		
			Session session = null;
			List<Aluno> alunos = new ArrayList<Aluno>();
			Query query = session.createQuery("from Aluno");
							
							

			alunos = query.list();
			
			return alunos;
	
	}
	
	public Boolean salva(Aluno aluno){
		
		Session session = null;
	
		session.save(aluno);
		
		return true;

}
	
public Boolean atualiza(Aluno aluno){
		
		Session session = null;
	
		session.merge(aluno);
		
		return true;

}

}
