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
import com.serpro.depae.treinamento.webescola.business.TurmaBC;
import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Turma;
import com.serpro.depae.treinamento.webescola.message.InfoMessages;


@ViewController
@RequestScoped
@PreviousView("/aluno_list.xhtml")
public class AlunoEditMB extends AbstractEditPageBean<Aluno, Long>{

	@Inject
	private AlunoBC bc;
	
	@Inject
	private TurmaBC turmaBC;

	@Inject
	private MessageContext messages;
	
	@Inject
	private Logger logger;
	
	
	@Override
	public String delete() {
		this.bc.removeAlunoDasTurmas(getId());
		this.bc.delete(getId());
		return getPreviousView();
	}

	
	@Override
	public String insert() {
		//TODO: verificar por que getBean nao funciona como argumento do insert
		Aluno a = new Aluno();
		a.setNome(getBean().getNome());
		a.setCpf(getBean().getCpf());
		
		this.bc.insert(a);
		//this.bc.insert(getBean());
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

	public List<Turma> getTurmasDisponiveis(){
		List disponiveis = turmaBC.findAll();
		disponiveis.removeAll(getBean().getTurmas());
		return disponiveis;
	}
	
	@ExceptionHandler
	public void handler(Throwable e) {
		messages.add(e.getMessage(), SeverityType.ERROR);
	}
	
	//se nao chamar esse método
	//as tabelas exibidas em aluno_edit.xhtml
	//não serão atualizadas
	private void updateBean(Long id) {
		setBean(this.bc.load(id));
	}
	
	
	public void matricularAjax(Long id){
		Turma d = turmaBC.load(id);
		turmaBC.matricularAluno(d.getId(), getBean().getId());
		updateBean(getBean().getId());
	}
	
	public void desmatricularAjax(Long id){
		Turma d = turmaBC.load(id);
		turmaBC.removerAlunoDaTurma(d.getId(), getBean().getId());
		updateBean(getBean().getId());
	}
	
}
