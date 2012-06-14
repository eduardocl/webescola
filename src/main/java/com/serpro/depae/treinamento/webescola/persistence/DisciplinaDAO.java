package com.serpro.depae.treinamento.webescola.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import com.serpro.depae.treinamento.webescola.domain.Disciplina;

@PersistenceController
public class DisciplinaDAO extends JPACrud<Disciplina, Long>{

	private static final long serialVersionUID = 2845674256596349623L;

	
	
	public Disciplina findByName(String nome){
		return null;
	}
	
}
