package br.alunos.dao;

import java.util.ArrayList;
import java.util.List;

import br.alunos.modelo.Aluno;

public class AlunoDAOFalso {
	
	public static List<Aluno> alunos = new ArrayList<Aluno>();
	
	public void salva(Aluno aluno){
		alunos.add(aluno);
	}
	
	
}
