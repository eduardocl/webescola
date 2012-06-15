package com.serpro.depae.treinamento.webescola.security;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;
 
 
@Alternative
public class Autenticador implements Authenticator{

	private static final long serialVersionUID = 1L;

	@Inject
	private Credenciais credenciais;
	
	@Override
	public boolean authenticate() {
		System.out.println("authenticate...");
		if(credenciais.getSenha().equalsIgnoreCase("secreta")) {
			return true;
		}
		return false;
	}

	@Override
	public void unAuthenticate() {
	}

	@Override
	public User getUser() {
		System.out.println("obtendo usuario....");
		return new User() {
			
			@Override
			public void setAttribute(Object key, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getAttribute(Object key) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
