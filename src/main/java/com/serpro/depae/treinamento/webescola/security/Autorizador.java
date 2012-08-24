package com.serpro.depae.treinamento.webescola.security;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.security.SecurityContext;

  
@Alternative
public class Autorizador implements Authorizer{
	private static final long serialVersionUID = 1L;

		
	@Inject
	private SecurityContext securityContext;
	
	@Override
	public boolean hasRole(String role) {
		return true;
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		System.out.println("resource="+resource + " operation="+operation );
		//if(securityContext.isLoggedIn() == false) {
		//	System.out.println("hasPermission... fails");	
		//	return false;
		//}
		return true;
	}

}
