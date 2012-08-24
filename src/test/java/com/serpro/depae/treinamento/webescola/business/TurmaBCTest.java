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
import com.serpro.depae.treinamento.webescola.domain.Turma;
import com.serpro.depae.treinamento.webescola.exception.AlunoDuplicadoException;
import com.serpro.depae.treinamento.webescola.exception.BusinessException;
import com.serpro.depae.treinamento.webescola.security.Credenciais;
 
@RunWith(DemoiselleRunner.class)
public class TurmaBCTest {

	@Inject
	private TurmaBC bc;
	
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
		bc.insert(new Turma("matematica"));
		Turma d = bc.findByName("matematica");
		assertNotNull(d);
		bc.delete(d.getId());
	}
	
	
	@Test(expected=BusinessException.class)
	public void turmaComNomeInvalidoFalha() {
		securityContext.login();

		bc.insert(new Turma());
	}
	
	@Test(expected=BusinessException.class)
	public void criarTurmaComNomeRepetidoFalha(){
		bc.insert(new Turma("matematica"));
		bc.insert(new Turma("matematica"));
	}
	
	@Test
	public void updateTurmaComSucesso() {
		bc.insert(new Turma("d1"));
		Turma d = bc.findByName("d1");
		d.setNome("d2");
		bc.update(d);
		d = bc.findByName("d2");
		assertNotNull(d);
		bc.delete(d.getId());
	}
	
	@Test(expected=BusinessException.class)
	public void updateTurmaComNomeRepetidoFalha() {
		bc.insert(new Turma("d1"));
		bc.insert(new Turma("d2"));
		
		Turma d = bc.findByName("d1");
		bc.update(d);
		d.setNome("d2");
		bc.update(d);
	}
	
	
	
	
	@Test
	public void matricularAlunoComSucesso() {
		alunoBC.insert(new Aluno("fulano","111"));
		alunoBC.insert(new Aluno("fulano", "111"));
		
		bc.matricularAluno(bc.findAll().get(0).getId(), 
				alunoBC.findAll().get(0).getId());
	
		bc.matricularAluno(bc.findAll().get(0).getId(), 
				alunoBC.findAll().get(1).getId());
		
		Turma d = bc.findAll().get(0);
		assertEquals(d.getAlunos().size(), 2);
		
		Aluno a = alunoBC.findAll().get(0);
		assertEquals(a.getTurmas().size(), 1);
	}

	@Test(expected=AlunoDuplicadoException.class)
	public void matricularAlunoDuasVezesFalha() {
		bc.insert(new Turma("matematica"));
		alunoBC.insert(new Aluno("fulano"));
		
		Aluno aluno = alunoBC.findByName("fulano");
		Turma turma = bc.findByName("matematica");
		
		bc.matricularAluno(turma.getId(), aluno.getId());
		bc.matricularAluno(turma.getId(), aluno.getId());
		
	}
	
	
	@After
	public void clean() {
		for(Turma d: bc.findAll()) {
			bc.delete(d.getId());
		}
	}
	
}
