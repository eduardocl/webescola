package com.serpro.depae.treinamento.webescola.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.FileRenderer;

import com.serpro.depae.treinamento.webescola.business.TurmaBC;
import com.serpro.depae.treinamento.webescola.domain.Turma;

@ViewController
@NextView("/turma_edit.xhtml")
@PreviousView("/turma_list.xhtml")
public class TurmaListMB extends AbstractListPageBean<Turma, Long>{

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaBC bc;
	
	@Inject
	//@Path("reports/report1.jrxml")
	private Report turmasnew;

	
	@Inject
	private FileRenderer renderer;
	
	
	@Override
	protected List<Turma> handleResultList() {
		return this.bc.findAll();
	}
	
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				bc.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}
	
	
	public String showReport() throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();

		byte[] buffer = this.turmasnew.export(getResultList(), param, Type.PDF);
		this.renderer.render(buffer, FileRenderer.ContentType.PDF, "relatorio.pdf");

		
		return getNextView();
		
		
		
	}
	
}
