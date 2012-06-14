package com.serpro.depae.treinamento.webescola.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(rollback=true, severity=SeverityType.ERROR)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}
}
