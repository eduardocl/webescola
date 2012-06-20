package com.serpro.depae.treinamento.webescola.view;

import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

import com.serpro.depae.treinamento.webescola.business.AlunoBC;
import com.serpro.depae.treinamento.webescola.business.DisciplinaBC;
import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Disciplina;
import com.serpro.depae.treinamento.webescola.message.InfoMessages;


@ViewController
@RequestScoped
@PreviousView("/aluno_list.xhtml")
public class AlunoEditMB extends AbstractEditPageBean<Aluno, Long>{

	@Inject
	private AlunoBC bc;
	
	@Inject
	private DisciplinaBC disciplinaBC;

	@Inject
	private MessageContext messages;
	
	@Inject
	private Logger logger;
	
	
	@Override
	public String delete() {
		this.bc.delete(getId());
		return getPreviousView();
	}

	
	@Override
	public String insert() {
		this.bc.insert(getBean());
		messages.add(InfoMessages.ALUNO_CRIADO_OK, getBean().getNome());
		return getPreviousView();
	}

	@Override
	public String update() {
		this.bc.update(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(this.bc.load(getId()));
	}

	public String desmatricular(Long disciplinaId) {
		Disciplina d = disciplinaBC.load(disciplinaId);
		disciplinaBC.removerAlunoDaDisciplina(d.getId(), getBean().getId());
		updateBean(getBean().getId());
		messages.add(InfoMessages.ALUNO_DESMATRICULADO_OK, getBean().getNome(), d.getNome());
		return null; 
	}
	
	
	public String matricular(Long disciplinaId) {
		Disciplina d = disciplinaBC.load(disciplinaId);
		disciplinaBC.matricularAluno(d.getId(), getBean().getId());
		messages.add(InfoMessages.ALUNO_MATRICULADO_OK, getBean().getNome(), d.getNome());
		//comentar essa linha mostra mensagem de aluno duplicado na tela 
		updateBean(getBean().getId());
		return null;
	}
	
	
	public List<Disciplina> getDisciplinasDisponiveis(){
		List disponiveis = disciplinaBC.findAll();
		disponiveis.removeAll(getBean().getDisciplinas());
		return disponiveis;
	}
	
	@ExceptionHandler
	public void handler(Throwable e) {
		messages.add(e.getMessage(), SeverityType.ERROR);
	}
	
	
	private void updateBean(Long id) {
		setBean(this.bc.load(id));
	}
	
}
