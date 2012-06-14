package com.serpro.depae.treinamento.webescola.business;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

import com.serpro.depae.treinamento.webescola.domain.Disciplina;
import com.serpro.depae.treinamento.webescola.exception.BusinessException;

@RunWith(DemoiselleRunner.class)
public class DisciplinaBCTest {

	@Inject
	private DisciplinaBC bc;
	
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
		bc.updateDisciplina(d.getId(), "d2");
		d = bc.findByName("d2");
		assertNotNull(d);
		bc.delete(d.getId());
	}
	
	@Test(expected=BusinessException.class)
	public void updateDisciplinaComNomeRepetidoFalha() {
		bc.insert(new Disciplina("d1"));
		bc.insert(new Disciplina("d2"));
		
		Disciplina d = bc.findByName("d1");
		bc.updateDisciplina(d.getId(), "d1");
		bc.updateDisciplina(d.getId(), "d2");
	}
	
	@After
	public void clean() {
		for(Disciplina d: bc.findAll()) {
			bc.delete(d.getId());
		}
	}
	
}
