package com.serpro.depae.treinamento.webescola.view;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

import com.serpro.depae.treinamento.webescola.business.DisciplinaBC;
import com.serpro.depae.treinamento.webescola.domain.Disciplina;

@ViewController
@PreviousView("/disciplina_list.xhtml")
public class DisciplinaEditMB extends AbstractEditPageBean<Disciplina, Long>{

	@Inject
	private DisciplinaBC bc;
	
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
