package com.serpro.depae.treinamento.webescola.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
 
@SessionScoped
public class Credenciais implements Serializable {

	private String login;
	private String senha;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void clear() {
		login = null;
		senha = null;
	}
	
}
