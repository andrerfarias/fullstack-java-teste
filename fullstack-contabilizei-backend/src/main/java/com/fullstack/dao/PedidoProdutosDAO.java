/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.dao;

import com.fullstack.model.PedidoProdutos;
import javax.persistence.NoResultException;

/**
 *
 * @author Andre
 */
public class PedidoProdutosDAO extends Connector implements DAO{

    @Override
    public PedidoProdutos getById(Long id) {
        try{
            return getEm().createQuery("SELECT pedidoP FROM PedidoProdutos pedidoP WHERE pedidoP.id = :id ", PedidoProdutos.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public PedidoProdutos incluir(Object entity) {
        return (PedidoProdutos) include(entity);
    }

    @Override
    public PedidoProdutos atualizar(Object entity) {
        return (PedidoProdutos) update(entity);
    }
    
    
}
