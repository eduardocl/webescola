package com.serpro.depae.treinamento.webescola.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.Message;
import br.gov.frameworkdemoiselle.message.SeverityType;


public class DisciplinaLotadaException extends BusinessException {

	public DisciplinaLotadaException(Message message, Object... params) {
		super(message, params);
	}

	private static final long serialVersionUID = 1L;
	
}
