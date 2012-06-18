package com.serpro.depae.treinamento.webescola.view;

import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

import com.serpro.depae.treinamento.webescola.business.AlunoBC;
import com.serpro.depae.treinamento.webescola.business.DisciplinaBC;
import com.serpro.depae.treinamento.webescola.domain.Aluno;


@ViewController
@PreviousView("/aluno_list.xhtml")
public class AlunoEditMB extends AbstractEditPageBean<Aluno, Long>{

	@Inject
	private AlunoBC bc;
	
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

}
