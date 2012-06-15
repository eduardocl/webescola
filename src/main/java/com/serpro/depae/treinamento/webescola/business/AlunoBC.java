package com.serpro.depae.treinamento.webescola.business;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.persistence.AlunoDAO;


@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO>{

	
	@Startup
	public void inserirAlunos() {
		this.getDelegate().insert(new Aluno("João"));
		this.getDelegate().insert(new Aluno("Maria"));
		this.getDelegate().insert(new Aluno("José"));
	}
	
	
}
