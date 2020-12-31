package com.jsfcrud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jsfcrud.model.JPAUtil;
import com.jsfcrud.model.entitie.Client;

public class ClientDAO {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

	//Salvar cliente
	public void store(Client client) {
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		entityManager.persist(client);
		entityManager.getTransaction().commit();
//		JPAUtil.closeConnection();
	}

	//Editar cliente
	public void update(Client client) {
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		entityManager.merge(client);
		entityManager.getTransaction().commit();
//		JPAUtil.closeConnection();
	}
	
	//Buscar cliente	
	public Client find (long id) {
		Client client;
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		client = entityManager.find(Client.class, id);
//		JPAUtil.closeConnection();
		return client;
	}
	
	//Ler todos
	public List<Client> read(){
		List<Client> clients;
		Query query = entityManager.createQuery("SELECT c FROM Client c");
		clients = query.getResultList();
		return clients;
	}
	
	//Apagar registro
	public void delete (long id) {
		Client client = find(id);
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		entityManager.remove(client);
		entityManager.getTransaction().commit();
	}
}
