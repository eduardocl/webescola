package com.serpro.depae.treinamento.webescola.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.gov.frameworkdemoiselle.validation.annotation.Cpf;

@Entity
public class Aluno {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;
	
	@Cpf
	private String cpf;
	
	
	@ManyToMany(mappedBy="alunos")
	private List<Disciplina> disciplinas;
	
	public Aluno() {}
	
	public Aluno(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Disciplina> getDisciplinas() {
		if(this.disciplinas == null) {
			this.disciplinas = new ArrayList<Disciplina>();
		}
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
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

	public void removeDisciplina(Disciplina disciplina) {
		this.disciplinas.remove(disciplina);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
