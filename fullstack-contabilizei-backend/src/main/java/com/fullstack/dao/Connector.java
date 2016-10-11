/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author andre.farias
 */
public class Connector {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("contabilizei");
    EntityManager entityManager = factory.createEntityManager();

    public EntityManager getEm(){
        return this.entityManager;
    }
    
    public void closeEm(){
        this.entityManager.close();
    }
    
    /**
     * Inclui um novo registro no banco de dados
     * @author André Farias
     * @param entity
     * @return O objeto incluído
     */
    public Object include(Object entity){
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
            return entity;
        } catch (Exception ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    /**
     * Atualiza uma entidade no Banco de Dados
     * @author André Farias
     * @param entity
     * @return O objeto atualizado
     */
    public Object update(Object entity){
        this.entityManager.getTransaction().begin();
        Object newEntity = entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
        return newEntity;
    }
    
}
