package com.serpro.depae.treinamento.webescola.view;

import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

import com.serpro.depae.treinamento.webescola.business.AlunoBC;
import com.serpro.depae.treinamento.webescola.business.DisciplinaBC;
import com.serpro.depae.treinamento.webescola.domain.Aluno;


@ViewController
@RequestScoped
@PreviousView("/aluno_list.xhtml")
public class AlunoEditMB extends AbstractEditPageBean<Aluno, Long>{

	@Inject
	private AlunoBC bc;
	
	@Inject
	private DisciplinaBC disciplinaBC;
	
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

	/**
	 * Responde a um request ajax ná pagina aluno_edit.xhtml
	 * 
	 * @param disciplinaId
	 */
	public void desmatricular(ActionEvent event) {
		Long disciplinaId = this.getBean().getDisciplinas().get(0).getId();
		disciplinaBC.removerAlunoDaDisciplina(disciplinaId, getBean().getId());
		//return "/aluno_edit.xhtml";
	}

	public void desmatricularAction(Long disciplinaId) {
		//disciplinaId = this.getBean().getDisciplinas().get(0).getId();
		disciplinaBC.removerAlunoDaDisciplina(disciplinaId, getBean().getId());
	}

	
}
