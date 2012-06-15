package com.serpro.depae.treinamento.webescola.business;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.message.Message;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Disciplina;
import com.serpro.depae.treinamento.webescola.persistence.AlunoDAO;
import com.serpro.depae.treinamento.webescola.security.Credenciais;


@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO>{

	
	@Inject
	private DisciplinaBC disciplinaBC;
	
	@Inject
	private MessageContext messageContext;

	
	@Inject
	private Logger logger;
	
	@Startup
	public void inserirAlunos() {
		this.getDelegate().insert(new Aluno("João"));
		this.getDelegate().insert(new Aluno("Maria"));
		this.getDelegate().insert(new Aluno("José"));
	}
	
		
	@Transactional
	public void matricularAluno(Long disciplinaId, Long alunoId) {
		Disciplina disciplina = disciplinaBC.load(disciplinaId);
		Aluno aluno = this.getDelegate().load(alunoId);
		
		if(disciplina == null || aluno == null) {
			messageContext.add("Problemas ao matricular o aluno", SeverityType.FATAL, null);
		} else {
			disciplina.getAlunos().add(aluno);
			aluno.getDisciplinas().add(disciplina);
			disciplinaBC.update(disciplina);
			this.getDelegate().update(aluno);
		}
	}
	
	
}
