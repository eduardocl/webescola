package com.serpro.depae.treinamento.webescola.domain;

import java.util.ArrayList;
import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.xml.bind.annotation.XmlAttribute;

@Decorator
public class AlunoDecorator extends Aluno {

	@Inject
	@Delegate 
	private Aluno aluno;


	public Long getId() {
		return aluno.getId();
	}

	public void setId(Long id) {
		this.aluno.setId(id);
	}

	@XmlAttribute
	public String getNome() {
		System.out.println("aluno getNome");
		return this.aluno.getNome() + " Decorator";
	}

	public void setNome(String nome) {
		this.aluno.setNome(nome);
	}
	
	public List<Turma> getTurmas() {
		if(this.aluno.getTurmas() == null) {
			this.aluno.setTurmas(new ArrayList<Turma>());
		}
		return this.aluno.getTurmas();
	}

	public void setTurmas(List<Turma> turmas) {
		this.aluno.setTurmas(turmas);
	}

	public void removeTurma(Turma turma) {
		this.aluno.getTurmas().remove(turma);
	}

	@XmlAttribute
	public String getCpf() {
		return this.aluno.getCpf();
	}

	public void setCpf(String cpf) {
		this.aluno.setCpf(cpf);
	}

}
