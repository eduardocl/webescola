package com.serpro.depae.treinamento.webescola.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.FileRenderer;

import com.serpro.depae.treinamento.webescola.business.TurmaBC;
import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Turma;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
		
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("turma", Turma.class);
		xstream.alias("aluno", Aluno.class);
		String xml = xstream.toXML(getResultList());
		
		StringBuffer bufferStr = new StringBuffer();
		bufferStr.append("<turmas>\n");
		
		for(Turma turma: getResultList()) {
			bufferStr.append(turma.toXML());
		}
		
		bufferStr.append("</turmas>\n");
		
		System.out.println(bufferStr.toString());
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = (Document) docBuilder.parse( new InputSource(new StringReader(bufferStr.toString())));
				
		
		JRXmlDataSource ds = new JRXmlDataSource(doc);
		
		List<JRXmlDataSource> xmlDS = new ArrayList<JRXmlDataSource>();
		xmlDS.add(ds);
		
		JasperReport report = (JasperReport) this.turmasnew.getReportObject();
		JasperPrint jasperPrint = JasperFillManager.fillReport(report,new HashMap(), ds);
		
		//byte[] buffer = this.turmasnew.export(xmlDS, param, Type.PDF);
		//	this.renderer.render(buffer, FileRenderer.ContentType.PDF, "relatorio.pdf");

			OutputStream output = new FileOutputStream(new File("/home/82035644020/Desktop/relatorio.pdf")); 
			JasperExportManager.exportReportToPdfStream(jasperPrint, output);	
		
		return getNextView();
		
		
		
	}
	
}
