package com.serpro.depae.treinamento.webescola.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;

import com.serpro.depae.treinamento.webescola.configuration.DisciplinasConfig;
import com.serpro.depae.treinamento.webescola.exception.AlunoDuplicadoException;
import com.serpro.depae.treinamento.webescola.exception.DisciplinaLotadaException;

@Controller
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DisciplinasConfig config;

	@Inject Logger logger;
	
	private List<String> alunos;

	public Disciplina() {
		this.alunos = new ArrayList<String>();
	}

	public void matricularAluno(String aluno)  {
		if (alunos.contains(aluno)) {
			logger.info("Aluno " + aluno +" já está matriculado");
			throw new AlunoDuplicadoException("Aluno " + aluno + " já está matriculado.");
		} else if(estaCheia()){ 
			logger.info("A disciplina está lotada");
			throw new DisciplinaLotadaException("Disciplina está lotada");
		}else{
			alunos.add(aluno);
			logger.info("Aluno " + aluno +" matriculado com sucesso");
		}
	}

	
	private boolean estaCheia(){
		return alunos.size() == config.getMaxAlunos();
	}
	
	@ExceptionHandler
	public void handleException(AlunoDuplicadoException e) {
		logger.info("Aluno duplicado");
		throw e;
	}
	
	@ExceptionHandler
	public void disciplinaLotadaExceptionHandler(DisciplinaLotadaException e) {
		logger.info("Disciplina está lotada");
		throw e;
	}
	
	
}
