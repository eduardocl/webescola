package com.serpro.depae.treinamento.webescola.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.util.FileRenderer;

import com.serpro.depae.treinamento.webescola.business.TurmaBC;
import com.serpro.depae.treinamento.webescola.domain.Turma;

public class TurmaReport {

	//@Inject
	//private TurmaBC turmaBC;

	@Inject
	private FileRenderer renderer;

	@Inject
	//@Path("reports/turmas.jrxml")
	private Report turmas; // tem quer ser esse nome

	public void getReport() {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			byte[] buffer = this.turmas.export(getResultList(), param, Type.PDF);
		} catch (Exception e) {
			// Trata a exce##o.
		}
	}

	private List<Turma> getResultList() {
		//return turmaBC.findAll();
		
		List<Turma> turmas = new ArrayList<Turma>();
		turmas.add(new Turma("tste"));
		turmas.add(new Turma("tste1"));
		return turmas;
	}

	public String showReport() {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			byte[] buffer = this.turmas.export(getResultList(), param, Type.PDF);
			this.renderer.render(buffer, FileRenderer.ContentType.PDF, "relatorio.pdf");
		} catch (Exception e) {
			
		}
		return null;
	}

	
	public void testReport() {
		turmas.prepare(getResultList(),null);
		JasperViewer.viewReport((JasperPrint)turmas.getReportObject());
	}

	
}
