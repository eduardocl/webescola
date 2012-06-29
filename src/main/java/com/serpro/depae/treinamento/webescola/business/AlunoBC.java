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
	private Instance<TurmaBC> turmaBC;
	
	@Startup
	public void inserirAlunos() {
		this.getDelegate().insert(new Aluno("João",  "1122234"));
		this.getDelegate().insert(new Aluno("Maria", "2333444"));
		this.getDelegate().insert(new Aluno("José",  "5656778"));
		
		turmaBC.get().matricularAluno(1L,1L);
		turmaBC.get().matricularAluno(1L,2L);
		turmaBC.get().matricularAluno(1L,3L);
		
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
