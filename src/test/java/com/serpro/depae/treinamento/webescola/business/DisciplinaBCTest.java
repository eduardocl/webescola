package com.serpro.depae.treinamento.webescola.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.security.SecurityContext;

import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Disciplina;
import com.serpro.depae.treinamento.webescola.exception.AlunoDuplicadoException;
import com.serpro.depae.treinamento.webescola.exception.BusinessException;
import com.serpro.depae.treinamento.webescola.security.Credenciais;
 
@RunWith(DemoiselleRunner.class)
public class DisciplinaBCTest {

	@Inject
	private DisciplinaBC bc;
	
	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	private Credenciais credenciais;
	
	@Inject
	private SecurityContext securityContext;
	
	@Before
	public void setUp() {
		credenciais.setLogin("usuario");
		credenciais.setSenha("secreta");
		securityContext.login();
	}
	
	
	@Test 
	public void testFindByName() {
		bc.insert(new Disciplina("matematica"));
		Disciplina d = bc.findByName("matematica");
		assertNotNull(d);
		bc.delete(d.getId());
	}
	
	
	@Test(expected=BusinessException.class)
	public void disciplinaComNomeInvalidoFalha() {
		bc.insert(new Disciplina());
	}
	
	@Test(expected=BusinessException.class)
	public void criarDisciplinaComNomeRepetidoFalha(){
		bc.insert(new Disciplina("matematica"));
		bc.insert(new Disciplina("matematica"));
	}
	
	@Test
	public void updateDisciplinaComSucesso() {
		bc.insert(new Disciplina("d1"));
		Disciplina d = bc.findByName("d1");
		d.setNome("d2");
		bc.update(d);
		d = bc.findByName("d2");
		assertNotNull(d);
		bc.delete(d.getId());
	}
	
	@Test(expected=BusinessException.class)
	public void updateDisciplinaComNomeRepetidoFalha() {
		bc.insert(new Disciplina("d1"));
		bc.insert(new Disciplina("d2"));
		
		Disciplina d = bc.findByName("d1");
		bc.update(d);
		d.setNome("d2");
		bc.update(d);
	}
	
	
	
	
	@Test
	public void matricularAlunoComSucesso() {
		alunoBC.insert(new Aluno("fulano"));
		alunoBC.insert(new Aluno("fulano"));
		bc.insert(new Disciplina("disciplina de teste"));
		
		bc.matricularAluno(bc.findAll().get(0).getId(), 
				alunoBC.findAll().get(0).getId());
	
		bc.matricularAluno(bc.findAll().get(0).getId(), 
				alunoBC.findAll().get(1).getId());
		
		Disciplina d = bc.findAll().get(0);
		assertEquals(d.getAlunos().size(), 2);
		
		Aluno a = alunoBC.findAll().get(0);
		assertEquals(a.getDisciplinas().size(), 1);
	}

	@Test(expected=AlunoDuplicadoException.class)
	public void matricularAlunoDuasVezesFalha() {
		bc.insert(new Disciplina("matematica"));
		alunoBC.insert(new Aluno("fulano"));
		
		Aluno aluno = alunoBC.findByName("fulano");
		Disciplina disciplina = bc.findByName("matematica");
		
		bc.matricularAluno(disciplina.getId(), aluno.getId());
		bc.matricularAluno(disciplina.getId(), aluno.getId());
		
	}
	
	
	@After
	public void clean() {
		for(Disciplina d: bc.findAll()) {
			bc.delete(d.getId());
		}
	}
	
}
