package com.serpro.depae.treinamento.webescola.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.Message;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(rollback=true, severity=SeverityType.ERROR)
public class TurmaLotadaException extends BusinessException {

	public TurmaLotadaException(Message message, Object... params) {
		super(message, params);
	}

	private static final long serialVersionUID = 1L;
	
}
