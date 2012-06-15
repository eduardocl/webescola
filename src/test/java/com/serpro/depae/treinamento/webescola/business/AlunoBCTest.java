package com.serpro.depae.treinamento.webescola.business;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
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
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private Credenciais credenciais;
	
	
//	@Before
//	public void setUp() {
//		credenciais.setLogin("teste");
//		credenciais.setSenha("teste");
//		securityContext.login();
//	}
	
	
	@Test
	public void matricularAlunoComSucesso() {
		alunoDAO.insert(new Aluno("fulano"));
		alunoDAO.insert(new Aluno("fulano"));
		disciplinaDAO.insert(new Disciplina("matematica"));
		
		alunoBC.matricularAluno(disciplinaDAO.findAll().get(0).getId(), 
				alunoDAO.findAll().get(0).getId());
	
		alunoBC.matricularAluno(disciplinaDAO.findAll().get(0).getId(), 
				alunoDAO.findAll().get(1).getId());
		
		Disciplina d = disciplinaDAO.findAll().get(0);
		assertEquals(d.getAlunos().size(), 2);
	}
	
}
