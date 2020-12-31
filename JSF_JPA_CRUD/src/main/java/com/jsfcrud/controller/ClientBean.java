package com.jsfcrud.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.jsfcrud.dao.ClientDAO;
import com.jsfcrud.model.entitie.Client;

@ManagedBean (name = "clientBean")
@RequestScoped
public class ClientBean {
	
	public List<Client> getClients(){
		ClientDAO dao = new ClientDAO();
		List<Client> clients = dao.read(); 
		return clients;
	}
	
	public String create() {
		Client client = new Client();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("client", client);
		return "/faces/create.xhtml";
	}
	
	public String store(Client client) {
		ClientDAO dao = new ClientDAO();
		client.setRegisterDate(new java.sql.Date(new Date().getTime()));
		client.setEditDate(new java.sql.Date(new Date().getTime()));
		dao.store(client);
		return "/faces/index.xhtml";
	}
	
	public String edit(long id) {
		ClientDAO dao = new ClientDAO();
		Client client = new Client();
		client = dao.find(id);
		System.out.println(client);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("client", client);
		return "/faces/edit.xhtml";
	}
	
	public String update(Client client) {
		ClientDAO clientDAO = new ClientDAO();
		client.setEditDate(new java.sql.Date(new Date().getTime()));
		clientDAO.update(client);
		return "/faces/index.xhtml";
	}
	
	public String delete (long id) {
		ClientDAO dao = new ClientDAO();
		Client client = new Client();
		dao.delete(id);
		return "/faces/index.xhtml";
		
	}
}
