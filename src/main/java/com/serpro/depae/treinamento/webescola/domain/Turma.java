package com.serpro.depae.treinamento.webescola.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import br.gov.frameworkdemoiselle.transaction.Transactional;

@Entity
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="turma_aluno",
			joinColumns= {@JoinColumn(name="turma_id")},
			inverseJoinColumns= {@JoinColumn(name="aluno_id")})
	private List<Aluno> alunos;
	
	private String nome;
		
	public Turma(){}
	
	
	public Turma(String nome){
		this.setNome(nome);
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
	
	
	public void removeAluno(Aluno aluno) {
		this.alunos.remove(aluno);
		aluno.removeTurma(this);
	}
	


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Transactional
	public List<Aluno> getAlunos() {
		if(this.alunos == null) {
			this.alunos = new ArrayList<Aluno>();
		}
		return alunos;
	}


	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}


	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Turma)) {
			return false;
		}else {
			Turma a = (Turma)obj;
			if(a.getId().equals(this.getId())) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return this.nome;
	}

	
}
