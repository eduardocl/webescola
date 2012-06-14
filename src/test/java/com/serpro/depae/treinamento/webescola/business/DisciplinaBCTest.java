package com.serpro.depae.treinamento.webescola.business;

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
	public void criarDisciplinaComNomeRepetidoFalha(){
		Disciplina d1 = new Disciplina("matematica");
		bc.insert(d1);
	}
	
	
}
