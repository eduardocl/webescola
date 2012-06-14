package com.serpro.depae.treinamento.webescola.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(rollback=true, severity=SeverityType.ERROR)
public class BusinessException extends RuntimeException {

}
