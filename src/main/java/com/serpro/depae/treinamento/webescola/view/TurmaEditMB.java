package com.serpro.depae.treinamento.webescola.view;

import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

import com.serpro.depae.treinamento.webescola.business.TurmaBC;
import com.serpro.depae.treinamento.webescola.domain.Turma;

@ViewController
@PreviousView("/turma_list.xhtml")
public class TurmaEditMB extends AbstractEditPageBean<Turma, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaBC bc;

	private boolean renderizaBotao;
	
	
	@Override
	public String delete() {
		this.bc.delete(getId());
		return getPreviousView();
	}

	
	@Override
	public String insert() {
		//TODO: verificar por que dá erro qdo passa o getBean() direto
		this.bc.insert(new Turma(getBean().getNome()));
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

	
	
	public List<Turma> getListOfTurmas(){
		return bc.findAll();
	}
	
	
	public void setTurma(ValueChangeEvent event) {
		
		String value = (String)event.getNewValue();
		
		
		if(value.equals("2")) {
			setRenderizaBotao(false);
		}else {
			setRenderizaBotao(true);
		}
	}


	public boolean isRenderizaBotao() {
		return renderizaBotao;
	}


	public void setRenderizaBotao(boolean renderizaBotao) {
		this.renderizaBotao = renderizaBotao;
	}
	

	
	
}
