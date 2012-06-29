package com.serpro.depae.treinamento.webescola.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.gov.frameworkdemoiselle.transaction.Transactional;

@Entity
@XStreamAlias("turma")
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

	@XmlAttribute
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
	
	
	public String toXML() {
		StringBuffer buf = new StringBuffer();
		buf.append("<turma>\n");
		buf.append("\t\t<nome>"+this.getNome()+"</nome>\n");
		buf.append("\t\t<alunos>\n");
		
		for(Aluno aluno: this.getAlunos()) {
			buf.append("\t\t\t<aluno>\n");
			buf.append("\t\t\t\t<nome>"+aluno.getNome()+"</nome>\n");
			buf.append("\t\t\t\t<cpf>"+aluno.getCpf()+"</cpf>\n");
			buf.append("\t\t\t</aluno>\n");
		}
		
		buf.append("\t\t</alunos>\n");
		buf.append("</turma>\n");
		
		return buf.toString();
		
	}
	
	
}
