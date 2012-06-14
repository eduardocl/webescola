package com.serpro.depae.treinamento.webescola.security;

import javax.enterprise.inject.Alternative;

import br.gov.frameworkdemoiselle.security.Authorizer;

@Alternative
public class WebEscolaAutorizador implements Authorizer{
	private static final long serialVersionUID = 1L;

	@Override
	public boolean hasRole(String role) {
		return true;
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		System.out.println("hasPermission...");
		return true;
	}

}
