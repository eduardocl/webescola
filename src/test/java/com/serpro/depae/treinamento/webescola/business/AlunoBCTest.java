package com.serpro.depae.treinamento.webescola.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.security.SecurityContext;

import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Disciplina;
import com.serpro.depae.treinamento.webescola.persistence.AlunoDAO;
import com.serpro.depae.treinamento.webescola.persistence.DisciplinaDAO;
import com.serpro.depae.treinamento.webescola.security.Credenciais;

@RunWith(DemoiselleRunner.class)
public class AlunoBCTest {

	@Inject
	private AlunoDAO alunoDAO;
	
	@Inject 
	private DisciplinaDAO disciplinaDAO;
	
	@Inject
	private AlunoBC alunoBC;
	
//	@Before
//	public void setUp() {
//		credenciais.setLogin("teste");
//		credenciais.setSenha("teste");
//		securityContext.login();
//	}
	
	

	
}
