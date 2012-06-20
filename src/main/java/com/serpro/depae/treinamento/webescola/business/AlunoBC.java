package com.serpro.depae.treinamento.webescola.business;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.persistence.AlunoDAO;


@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO>{
	
	@Inject
	private MessageContext messageContext;
	
	@Inject 
	private Instance<DisciplinaBC> disciplinaBC;
	
	@Inject
	private Logger logger;
	
	@Startup
	public void inserirAlunos() {
		this.getDelegate().insert(new Aluno("João"));
		this.getDelegate().insert(new Aluno("Maria"));
		this.getDelegate().insert(new Aluno("José"));
		
		
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("Matemática").getId(), 
//				this.getDelegate().findByName("João").getId());
//	
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("Português").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("História").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("1").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("2").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("3").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("4").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("5").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("6").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("7").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("8").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("9").getId(), 
//				this.getDelegate().findByName("Maria").getId());
//		disciplinaBC.get().matricularAluno(disciplinaBC.get().findByName("10").getId(), 
//				this.getDelegate().findByName("Maria").getId());
		
	}
	
	
	public Aluno findByName(String name) {
		return this.getDelegate().findByName(name);
	}
	
}
