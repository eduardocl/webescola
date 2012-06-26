package com.serpro.depae.treinamento.webescola.persistence;

import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import com.serpro.depae.treinamento.webescola.domain.Turma;

@PersistenceController
public class TurmaDAO extends JPACrud<Turma, Long>{

	private static final long serialVersionUID = 2845674256596349623L;

	
	
	public Turma findByName(String nome){
		Query query = createQuery("select turma From Turma turma where nome = :nome");
		query.setParameter("nome", nome);
		List<Turma> result = query.getResultList();
		if(result == null || result.size() == 0){
			return null;
		}else{
			return result.get(0);
		}
	}
	
}


