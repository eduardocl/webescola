package com.serpro.depae.treinamento.webescola.configuration;

import br.gov.frameworkdemoiselle.configuration.Configuration;


@Configuration(resource="turma")
public class TurmasConfig {

	private Integer maxAlunos;

	public Integer getMaxAlunos() {
		return maxAlunos;
	}

	public void setMaxAlunos(Integer maxAlunos) {
		this.maxAlunos = maxAlunos;
	}
	
}
