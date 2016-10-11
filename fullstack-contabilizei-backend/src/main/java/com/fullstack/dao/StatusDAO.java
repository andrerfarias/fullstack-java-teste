/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.dao;

import com.fullstack.model.Status;
import javax.persistence.NoResultException;

/**
 *
 * @author Andre
 */
public class StatusDAO extends Connector implements DAO {

    @Override
    public Object getById(Long id) {
        try{
            return getEm().createQuery("SELECT status FROM Status status WHERE status.id = :id ", Status.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public Status incluir(Object entity) {
        return (Status) include(entity);
    }

    @Override
    public Object atualizar(Object entity) {
        return (Status) update(entity);
    }
    
    
}
