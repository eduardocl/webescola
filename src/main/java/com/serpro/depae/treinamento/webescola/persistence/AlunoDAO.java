package com.serpro.depae.treinamento.webescola.persistence;

import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import com.serpro.depae.treinamento.webescola.domain.Aluno;
import com.serpro.depae.treinamento.webescola.domain.Turma;

@PersistenceController
public class AlunoDAO extends JPACrud<Aluno, Long>{
	private static final long serialVersionUID = 1L;

	public Aluno findByName(String name) {
		Query query = createQuery("select aluno From Aluno aluno where nome = :nome");
		query.setParameter("nome", name);
		List<Aluno> result = query.getResultList();
		if(result == null || result.size() == 0){
			return null;
		}else{
			return result.get(0);
		}
	}

}
