package com.serpro.depae.treinamento.webescola.business;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

import com.serpro.depae.treinamento.webescola.domain.Disciplina;

@RunWith(DemoiselleRunner.class)
public class DisciplinaBCTest {

	@Inject
	private DisciplinaBC bc;
	
	
	@Test 
	public void testFindByName() {
		bc.insert(new Disciplina("matematica"));
		Disciplina d = bc.findByName("matematica");
		assertNotNull(d);
	}
	
	
	@Test
	public void criarDisciplinaComNomeRepetidoFalha(){
		
	}
	
	
}
