package com.serpro.depae.treinamento.webescola.business;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

import com.serpro.depae.treinamento.webescola.util.TurmaReport;

@RunWith(DemoiselleRunner.class)
public class ReportTest {

	@Inject
	private TurmaReport report;
	
	@Test
	public void testReport() {
		report.testReport();
	}
	
}
