package com.serpro.depae.treinamento.webescola.business;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

import com.serpro.depae.treinamento.webescola.configuration.TurmasConfig;
import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Turma;
import com.serpro.depae.treinamento.webescola.exception.AlunoDuplicadoException;
import com.serpro.depae.treinamento.webescola.exception.BusinessException;
import com.serpro.depae.treinamento.webescola.exception.TurmaLotadaException;
import com.serpro.depae.treinamento.webescola.message.InfoMessages;
import com.serpro.depae.treinamento.webescola.message.WarningMessages;
import com.serpro.depae.treinamento.webescola.persistence.TurmaDAO;

@BusinessController
public class TurmaBC extends DelegateCrud<Turma, Long, TurmaDAO>{

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	private MessageContext messageContext;

	@Inject
	private TurmasConfig config;
	
	@Inject
	@Name("error-messages")
	private ResourceBundle bundle;
	
	
	
	
	@Startup
	public void inserirTurmasExemplo() {
		this.insert(new Turma("Português"));
		this.insert(new Turma("Matemática"));
		this.insert(new Turma("História"));
		
		alunoBC.insert(new Aluno("João",  "1122234"));
		alunoBC.insert(new Aluno("Maria", "2333444"));
		alunoBC.insert(new Aluno("José",  "5656778"));
		
		this.matricularAluno(1L,1L);
		this.matricularAluno(1L,2L);
		this.matricularAluno(1L,3L);
	}
	
//	@Shutdown
//	public void terminarAplicacao() {
//		System.out.println("webescola sendo desligado!!!!!");
//	}

	@Inject
	private Logger logger;
	
	private void validate(Turma turma) throws BusinessException{
		
		if(turma.getNome() == null || turma.getNome().isEmpty()) {
			throw new BusinessException(bundle.getString("turma.nome.invalido"));
		}
		
		//inserindo nova disciplina
		if(getDelegate().findByName(turma.getNome()) != null && turma.getId() == null){
			throw new BusinessException(bundle.getString("turma.nome.repetido"));
		}
		
		//atualizando
		Turma d = getDelegate().findByName(turma.getNome());
		
		if( d!=null && (d.getId() != turma.getId()) && (turma.getNome().equals(d.getNome()))){
			throw new BusinessException(bundle.getString("turma.editar.nome.repetido"));
		}
	}
	

	//@RequiredPermission(resource="turma", operation="insert")
	@Override
	public void insert(Turma turma){
		validate(turma);
		super.insert(turma);
		messageContext.add("Turma criada com sucesso", SeverityType.INFO, null);	
	}
	
	protected Turma findByName(String nome) {
		return getDelegate().findByName(nome);
	}
	
	//@RequiredPermission(resource="disciplina", operation="update")
	@Override
	public void update(Turma turma) {
		validate(turma);
		getDelegate().update(turma);
	}
	
	
	
	@Transactional
	public void matricularAluno(Long turmaId, Long alunoId) {
		Turma turma = this.getDelegate().load(turmaId);
		Aluno aluno = alunoBC.load(alunoId);
		
		if(turma == null || aluno == null) {
			messageContext.add("Problemas ao matricular o aluno", SeverityType.FATAL);
		} else {
			
			if( turma.getAlunos().size() >= config.getMaxAlunos()) {
				throw new TurmaLotadaException(WarningMessages.TURMA_LOTADA, turma.getNome());
			}
			
			if(turma.getAlunos().contains(aluno)) {
				throw new AlunoDuplicadoException(WarningMessages.ALUNO_MATRICULADO_DUAS_VEZES, 
						aluno.getNome(), turma.getNome());
			}
						
			turma.getAlunos().add(aluno);
			aluno.getTurmas().add(turma);
			this.getDelegate().update(turma);
			messageContext.add(InfoMessages.ALUNO_MATRICULADO_OK, aluno.getNome(), turma.getNome());
		}
	}

	
	public void removerAlunoDaTurma(Long turmaID, Long alunoId) {
		Turma turma = this.getDelegate().load(turmaID);
		Aluno aluno = alunoBC.load(alunoId);
		turma.removeAluno(aluno);
		alunoBC.update(aluno);
		//this.getDelegate().update(disciplina);
		messageContext.add(InfoMessages.ALUNO_DESMATRICULADO_OK, aluno.getNome(), turma.getNome());
	}
	
	
	@ExceptionHandler
	public void businessExceptionHandler(BusinessException e) {
		logger.info(e.getMessage());
		//messageContext.add(e.getMessage(), SeverityType.ERROR);
		throw e;
	}

	@ExceptionHandler
	public void runtimeExceptionHandler(RuntimeException e) {
		//messageContext.add(e.getMessage(), SeverityType.FATAL);
		throw e;
	}
	
	@ExceptionHandler
	public void runtimeExceptionHandler(TurmaLotadaException e) {
		//messageContext.add(e.getMessage(), SeverityType.FATAL);
		throw e;
	}
	
	@ExceptionHandler
	public void runtimeExceptionHandler(AlunoDuplicadoException e) {
		//messageContext.add(e.getMessage(), SeverityType.FATAL);
		throw e;
	}
	
}
