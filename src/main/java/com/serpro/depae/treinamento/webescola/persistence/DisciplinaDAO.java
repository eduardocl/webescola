package com.serpro.depae.treinamento.webescola.persistence;

import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import com.serpro.depae.treinamento.webescola.domain.Disciplina;

@PersistenceController
public class DisciplinaDAO extends JPACrud<Disciplina, Long>{

	private static final long serialVersionUID = 2845674256596349623L;

	
	
	public Disciplina findByName(String nome){
		Query query = createQuery("select disciplina From Disciplina disciplina where nome = :nome");
		query.setParameter("nome", nome);
		List<Disciplina> result = query.getResultList();
		if(result == null || result.size() == 0){
			return null;
		}else{
			return result.get(0);
		}
	}
	
}


