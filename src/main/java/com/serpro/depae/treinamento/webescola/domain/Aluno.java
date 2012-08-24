package com.serpro.depae.treinamento.webescola.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.frameworkdemoiselle.validation.annotation.Cpf;

@Entity
public class Aluno {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name="nome")
	private String nome;
	
	@Column(name="cpf")
	//@Cpf descomente para que ocorra a validação
	private String cpf;
	
	
	@ManyToMany(mappedBy="alunos")
	private List<Turma> turmas;
	
	public Aluno() {}
	
	public Aluno(String nome) {
		this.nome = nome;
	}
	
	public Aluno(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlAttribute
	public String getNome() {
		System.out.println("aluno getNome");
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Turma> getTurmas() {
		if(this.turmas == null) {
			this.turmas = new ArrayList<Turma>();
		}
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Aluno)) {
			return false;
		}else {
			Aluno a = (Aluno)obj;
			if(a.getId().equals(this.getId())) {
				return true;
			}
		}
		return false;
	}

	public void removeTurma(Turma turma) {
		this.turmas.remove(turma);
	}

	@XmlAttribute
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
