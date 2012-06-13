package com.serpro.depae.treinamento.webescola.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;


public class AlunoDuplicadoException extends RuntimeException {

	public AlunoDuplicadoException(String message) {
		super(message);
	}
	
}
