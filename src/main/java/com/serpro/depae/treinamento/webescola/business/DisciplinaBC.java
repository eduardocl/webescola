package com.serpro.depae.treinamento.webescola.business;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import com.serpro.depae.treinamento.webescola.domain.Disciplina;
import com.serpro.depae.treinamento.webescola.exception.BusinessException;
import com.serpro.depae.treinamento.webescola.persistence.DisciplinaDAO;

@BusinessController
public class DisciplinaBC extends DelegateCrud<Disciplina, Long, DisciplinaDAO>{

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	private void validate(Disciplina disciplina){
		
		if(disciplina.getNome() == null || disciplina.getNome().isEmpty()) {
			throw new BusinessException("Disciplina com nome inválido");
		}
		
		//inserindo nova disciplina
		if(getDelegate().findByName(disciplina.getNome()) != null && disciplina.getId() == null){
			throw new BusinessException("Disciplina com nome repetido");
		}
		
		//atualizando
		Disciplina d = getDelegate().findByName(disciplina.getNome());
		
		if( d!=null && (d.getId() != disciplina.getId()) && (disciplina.getNome().equals(d.getNome()))){
			throw new BusinessException("Não foi possível atualizar a disciplina: nome já existe.");
		}
	}
	
	@Override
	public void insert(Disciplina disciplina){
		validate(disciplina);
		super.insert(disciplina);
	}
	
	public Disciplina findByName(String nome) {
		return getDelegate().findByName(nome);
	}
	
	public void updateDisciplina(Long id, String nome) {
		Disciplina d = getDelegate().load(id);
		d.setNome(nome);
		validate(d);
		getDelegate().update(d);
	}
	
	
	@ExceptionHandler
	public void businessExceptionHandler(BusinessException e) {
		logger.info(e.getMessage());
		throw e;
	}
	
	
	
}
