package com.serpro.depae.treinamento.webescola.message;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.Message;
import br.gov.frameworkdemoiselle.message.SeverityType;


public interface InfoMessages {

	final Message ALUNO_CRIADO_OK = new DefaultMessage("{aluno.criado.ok}", SeverityType.INFO);
	final Message ALUNO_MATRICULADO_OK = new DefaultMessage("{aluno.matriculado.ok}", SeverityType.INFO);
	final Message ALUNO_DESMATRICULADO_OK = new DefaultMessage("{aluno.desmatriculado.ok}", SeverityType.INFO);
	
	final Message TURMA_CRIADA_OK = new DefaultMessage("{turma.criada.ok}", SeverityType.INFO);
	
}
