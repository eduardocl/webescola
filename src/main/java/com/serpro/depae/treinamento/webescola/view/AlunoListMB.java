package com.serpro.depae.treinamento.webescola.view;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

import com.serpro.depae.treinamento.webescola.business.AlunoBC;
import com.serpro.depae.treinamento.webescola.domain.Aluno;

@ViewController
@NextView("/aluno_edit.xhtml")
@PreviousView("/aluno_list.xhtml")
public class AlunoListMB extends AbstractListPageBean<Aluno, Long>{

	@Inject
	private AlunoBC alunoBC;
	
	@Override
	protected List<Aluno> handleResultList() {
		return alunoBC.findAll();
	}

}
