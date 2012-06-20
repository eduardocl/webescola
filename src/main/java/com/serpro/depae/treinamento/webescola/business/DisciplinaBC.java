package com.serpro.depae.treinamento.webescola.business;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

import com.serpro.depae.treinamento.webescola.configuration.DisciplinasConfig;
import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Disciplina;
import com.serpro.depae.treinamento.webescola.exception.AlunoDuplicadoException;
import com.serpro.depae.treinamento.webescola.exception.BusinessException;
import com.serpro.depae.treinamento.webescola.exception.DisciplinaLotadaException;
import com.serpro.depae.treinamento.webescola.message.WarningMessages;
import com.serpro.depae.treinamento.webescola.persistence.DisciplinaDAO;

@BusinessController
public class DisciplinaBC extends DelegateCrud<Disciplina, Long, DisciplinaDAO>{

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	private MessageContext messageContext;

	@Inject
	private DisciplinasConfig config;
	
	@Inject
	@Name("error-messages")
	private ResourceBundle bundle;
	
	@Startup
	public void inserirDisciplinasExemplo() {
		this.insert(new Disciplina("Português"));
		this.insert(new Disciplina("Matemática"));
		this.insert(new Disciplina("História"));
//		this.insert(new Disciplina("1"));
//		this.insert(new Disciplina("2"));
//		this.insert(new Disciplina("3"));
//		this.insert(new Disciplina("4"));
//		this.insert(new Disciplina("5"));
//		this.insert(new Disciplina("6"));
//		this.insert(new Disciplina("7"));
//		this.insert(new Disciplina("8"));
//		this.insert(new Disciplina("9"));
//		this.insert(new Disciplina("10"));
//		this.insert(new Disciplina("11"));
//		this.insert(new Disciplina("12"));
//		this.insert(new Disciplina("13"));
	}
	
//	@Shutdown
//	public void terminarAplicacao() {
//		System.out.println("webescola sendo desligado!!!!!");
//	}

	@Inject
	private Logger logger;
	
	private void validate(Disciplina disciplina) throws BusinessException{
		
		if(disciplina.getNome() == null || disciplina.getNome().isEmpty()) {
			throw new BusinessException(bundle.getString("disciplina.nome.invalido"));
		}
		
		//inserindo nova disciplina
		if(getDelegate().findByName(disciplina.getNome()) != null && disciplina.getId() == null){
			throw new BusinessException(bundle.getString("disciplina.nome.repetido"));
		}
		
		//atualizando
		Disciplina d = getDelegate().findByName(disciplina.getNome());
		
		if( d!=null && (d.getId() != disciplina.getId()) && (disciplina.getNome().equals(d.getNome()))){
			throw new BusinessException(bundle.getString("disciplina.editar.nome.repetido"));
		}
	}
	

	//@RequiredPermission(resource="disciplina", operation="insert")
	@Override
	public void insert(Disciplina disciplina){
		validate(disciplina);
		super.insert(disciplina);
		messageContext.add("Disciplina criada com sucesso", SeverityType.INFO, null);
	}
	
	protected Disciplina findByName(String nome) {
		return getDelegate().findByName(nome);
	}
	
	//@RequiredPermission(resource="disciplina", operation="update")
	@Override
	public void update(Disciplina disciplina) {
		validate(disciplina);
		getDelegate().update(disciplina);
	}
	
	
	
	@Transactional
	public void matricularAluno(Long disciplinaId, Long alunoId) {
		Disciplina disciplina = this.getDelegate().load(disciplinaId);
		Aluno aluno = alunoBC.load(alunoId);
		
		if(disciplina == null || aluno == null) {
			messageContext.add("Problemas ao matricular o aluno", SeverityType.FATAL);
		} else {
			
			if( disciplina.getAlunos().size() >= config.getMaxAlunos()) {
				throw new DisciplinaLotadaException(WarningMessages.DISCIPLINA_LOTADA, disciplina.getNome());
			}
			
			if(disciplina.getAlunos().contains(aluno)) {
				throw new AlunoDuplicadoException(WarningMessages.ALUNO_MATRICULADO_DUAS_VEZES, 
						aluno.getNome(), disciplina.getNome());
			}
						
			disciplina.getAlunos().add(aluno);
			aluno.getDisciplinas().add(disciplina);
			this.getDelegate().update(disciplina);
		}
	}

	
	public void removerAlunoDaDisciplina(Long disciplinaId, Long alunoId) {
		Disciplina disciplina = this.getDelegate().load(disciplinaId);
		Aluno aluno = alunoBC.load(alunoId);
		disciplina.removeAluno(aluno);
		alunoBC.update(aluno);
		//this.getDelegate().update(disciplina);
	}
	
	
	@ExceptionHandler
	public void businessExceptionHandler(BusinessException e) {
		logger.info(e.getMessage());
		messageContext.add(e.getMessage(), SeverityType.WARN, null);
		throw e;
	}

	@ExceptionHandler
	public void runtimeExceptionHandler(RuntimeException e) {
		messageContext.add(e.getMessage(), SeverityType.FATAL);
	}
	
}
