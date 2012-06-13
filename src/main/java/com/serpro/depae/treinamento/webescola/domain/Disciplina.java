package com.serpro.depae.treinamento.webescola.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

import com.serpro.depae.treinamento.webescola.configuration.DisciplinasConfig;
import com.serpro.depae.treinamento.webescola.exception.AlunoDuplicadoException;
import com.serpro.depae.treinamento.webescola.exception.DisciplinaLotadaException;

@Controller
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DisciplinasConfig config;

	@Inject
	Logger logger;

	@Inject
	private ResourceBundle bundle;

	private List<String> alunos;

	public Disciplina() {
		this.alunos = new ArrayList<String>();
	}

	public void matricularAluno(String aluno) {
		if (alunos.contains(aluno)) {
			logger.info(bundle.getString("aluno.duplicado.erro"));
			throw new AlunoDuplicadoException(bundle.getString("aluno.duplicado.erro"));
		} else if (estaCheia()) {
			logger.info(bundle.getString("disciplina.lotada.erro"));
			throw new DisciplinaLotadaException(bundle.getString("disciplina.lotada.erro"));
		} else {
			alunos.add(aluno);
			logger.info("Aluno " + aluno + " matriculado com sucesso");
		}
	}

	private boolean estaCheia() {
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
