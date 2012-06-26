package com.serpro.depae.treinamento.webescola.view;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

import com.serpro.depae.treinamento.webescola.business.TurmaBC;
import com.serpro.depae.treinamento.webescola.domain.Turma;

@ViewController
@PreviousView("/turma_list.xhtml")
public class TurmaEditMB extends AbstractEditPageBean<Turma, Long>{

	@Inject
	private TurmaBC bc;
	
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
