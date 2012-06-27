package com.serpro.depae.treinamento.webescola.business;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Turma;
import com.serpro.depae.treinamento.webescola.persistence.AlunoDAO;


@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO>{
	
	@Inject
	private MessageContext messageContext;
	
	@Inject 
	private Instance<TurmaBC> turmaBC;
	
	@Inject
	private Logger logger;
	
	@Startup
	public void inserirAlunos() {
//		this.getDelegate().insert(new Aluno("João"));
//		this.getDelegate().insert(new Aluno("Maria"));
//		this.getDelegate().insert(new Aluno("José"));
	}
	
	
	public Aluno findByName(String name) {
		return this.getDelegate().findByName(name);
	}

	@Transactional
	public void removeAlunoDasTurmas(Long id) {
		Aluno aluno = this.load(id);
		for(Turma turma: aluno.getTurmas()) {
			turma.removeAluno(aluno);
			turmaBC.get().update(turma);
		}
	}
	
}
