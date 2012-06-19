package com.serpro.depae.treinamento.webescola.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany(cascade=CascadeType.REMOVE)
	@JoinTable(name="disciplina_aluno",
			joinColumns= {@JoinColumn(name="disciplina_id")},
			inverseJoinColumns= {@JoinColumn(name="aluno_id")})
	private Set<Aluno> alunos;
	
	private String nome;
		
	public Disciplina(){}
	
	
	public Disciplina(String nome){
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
		aluno.removeDisciplina(this);
	}
	


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Set<Aluno> getAlunos() {
		if(this.alunos == null) {
			this.alunos = new LinkedHashSet<Aluno>();
		}
		return alunos;
	}


	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}


	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Disciplina)) {
			return false;
		}else {
			Disciplina a = (Disciplina)obj;
			if(a.getId().equals(this.getId())) {
				return true;
			}
		}
		return false;
	}

	
}
