package com.serpro.depae.treinamento.webescola.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.security.SecurityContext;

import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Turma;
import com.serpro.depae.treinamento.webescola.persistence.AlunoDAO;
import com.serpro.depae.treinamento.webescola.persistence.TurmaDAO;
import com.serpro.depae.treinamento.webescola.security.Credenciais;

@RunWith(DemoiselleRunner.class)
public class AlunoBCTest {

	@Inject
	private AlunoBC alunoBC;
	
	@Test
	public void createAluno() {
		alunoBC.insert(new Aluno("teste","1111"));
	}
	
}
