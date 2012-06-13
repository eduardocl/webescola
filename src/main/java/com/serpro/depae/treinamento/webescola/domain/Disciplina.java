package com.serpro.depae.treinamento.webescola.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.serpro.depae.treinamento.webescola.configuration.DisciplinasConfig;

public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DisciplinasConfig config;

	@Inject Logger logger;
	
	private List<String> alunos;

	public Disciplina() {
		this.alunos = new ArrayList<String>();
	}

	public boolean matricularAluno(String aluno) {
		if (alunos.contains(aluno)) {
			logger.info("Aluno " + aluno +" já está matriculado");
			return false;
		} else if(estaCheia()){ 
			logger.info("A disciplina está lotada");
			return false;
		}else{
			boolean r = alunos.add(aluno);
			logger.info("Aluno " + aluno +" matriculado com sucesso");
			return r;
		}
	}

	
	private boolean estaCheia(){
		return alunos.size() == config.getMaxAlunos();
	}
	
}
