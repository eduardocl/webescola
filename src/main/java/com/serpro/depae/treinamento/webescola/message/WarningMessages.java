package com.serpro.depae.treinamento.webescola.message;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.Message;
import br.gov.frameworkdemoiselle.message.SeverityType;


public interface WarningMessages {

	
	final Message ALUNO_MATRICULADO_DUAS_VEZES = new DefaultMessage("{aluno.matriculado.duplicado}", SeverityType.WARN);
	
	final Message TURMA_LOTADA = new DefaultMessage("{turma.lotada}", SeverityType.WARN);
	
}
