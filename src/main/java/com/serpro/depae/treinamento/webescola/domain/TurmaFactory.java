package com.serpro.depae.treinamento.webescola.domain;

import java.util.ArrayList;
import java.util.List;


public class TurmaFactory {
	
	public static List<Turma> createBean() {
		Turma turma = new Turma("101");
		Aluno aluno = new Aluno("eduardo");
		aluno.setCpf("12223");
			
		turma.getAlunos().add(aluno);
		
		List<Turma> list = new ArrayList<Turma>();
		list.add(turma);
		return list;
	}
	
	
}
