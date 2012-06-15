package com.serpro.depae.treinamento.webescola.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import com.serpro.depae.treinamento.webescola.domain.Aluno;

@PersistenceController
public class AlunoDAO extends JPACrud<Aluno, Long>{
	private static final long serialVersionUID = 1L;

}
