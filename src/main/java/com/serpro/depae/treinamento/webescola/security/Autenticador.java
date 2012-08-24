package com.serpro.depae.treinamento.webescola.security;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;
 
 
@Alternative
public class Autenticador implements Authenticator{

	private static final long serialVersionUID = 1L;

	@Inject
	private Credenciais credenciais;
	
	@Override
	public boolean authenticate() {
		//if(credenciais.getSenha().equalsIgnoreCase(credenciais.getLogin())) {
			System.out.println("usuário autenticado");
			return true;
		//}
		//credenciais.clear();
		//System.out.println("autenticação falhou");
		//return false;
	}

	@Override
	public void unAuthenticate() {
		credenciais.clear();
	}

	@Override
	public User getUser() {
		if(credenciais.getLogin() == null && credenciais.getSenha() == null) {
			System.out.println("Usuário nulo");
			return null;
		}else {
			return new User() {
				
				@Override
				public void setAttribute(Object key, Object value) {
					
				}
				
				@Override
				public String getId() {
					return credenciais.getLogin();
				}
				
				@Override
				public Object getAttribute(Object key) {
					// TODO Auto-generated method stub
					return null;
				}
			};
		}
		
	}

}
