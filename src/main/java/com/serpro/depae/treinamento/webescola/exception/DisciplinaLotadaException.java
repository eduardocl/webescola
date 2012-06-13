package com.serpro.depae.treinamento.webescola.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(rollback=false, severity=SeverityType.ERROR)
public class DisciplinaLotadaException extends RuntimeException {
	public DisciplinaLotadaException(String message) {
		super(message);
	}
}
