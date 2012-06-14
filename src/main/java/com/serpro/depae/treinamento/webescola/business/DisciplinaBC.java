package com.serpro.depae.treinamento.webescola.business;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import com.serpro.depae.treinamento.webescola.domain.Disciplina;
import com.serpro.depae.treinamento.webescola.persistence.DisciplinaDAO;

@BusinessController
public class DisciplinaBC extends DelegateCrud<Disciplina, Long, DisciplinaDAO>{

	private static final long serialVersionUID = 1L;

	private void validate(Disciplina disciplina){
		if(getDelegate().findByName(disciplina.getNome()) != null){
			
		}
	}
	
	@Override
	public void insert(Disciplina disciplina){
		validate(disciplina);
	}
	
	
	@ExceptionHandler
	public void nomeDuplicadoExceptionHandler(){
		//....
	}
	
	
}
