package com.serpro.depae.treinamento.webescola.configuration;

import br.gov.frameworkdemoiselle.configuration.Configuration;


@Configuration(resource="disciplina")
public class DisciplinasConfig {

	private Integer maxAlunos;

	public Integer getMaxAlunos() {
		return maxAlunos;
	}

	public void setMaxAlunos(Integer maxAlunos) {
		this.maxAlunos = maxAlunos;
	}
	
}
