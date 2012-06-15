package com.serpro.depae.treinamento.webescola.view;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.NotLoggedInException;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

import com.serpro.depae.treinamento.webescola.security.Credenciais;

@ViewController
public class LoginMB {

	@Inject
	private Credenciais credenciais;
	
	@Inject 
	private SecurityContext securityContext;
	
	@Inject
	private MessageContext messages;
	
	@Inject
	private FacesContext facesContext;
	
	private String login;
	private String senha;
	private String msg;	
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
	public String login() {
		credenciais.setLogin(this.login);
		credenciais.setSenha(this.senha);
		try {
			securityContext.login();
			if(securityContext.isLoggedIn()) {
				setMsg(null);
				return "/index.jsf";
			}else {
				setMsg("Login falhou - login == senha");
				return "/login.jsf";
			}
			
		}catch(Exception e) {
			System.out.println("login falhou");
			setMsg("Login falhou - login == senha");
			return "/login.jsf";
		}
	}
	
	public String logout() {
		securityContext.logout();
		
		HttpSession sessao = (HttpSession) 
		        facesContext.getExternalContext().getSession(true);
		sessao.invalidate();
		
		setMsg("Até mais");
		return "/login.jsf";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
