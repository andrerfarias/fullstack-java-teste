package com.fullstack.dao;

import com.fullstack.model.Pedidos;
import com.fullstack.model.Produtos;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Andre
 */
public class ProdutoDAO extends Connector implements DAO{

    @Override
    public Object getById(Long id) {
        try{
            return getEm().createQuery("SELECT produto FROM Produtos produto WHERE produto.id = :id ", Produtos.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    /**
     * 
     * @return 
     */
    public List<Produtos> getAll(){
        return getEm().createQuery("SELECT p FROM Produtos p", Produtos.class).getResultList();
    }
    @Override
    public Produtos incluir(Object entity) {
        return (Produtos) include(entity);
    }

    @Override
    public Produtos atualizar(Object entity) {
        return (Produtos) update(entity);
    }
    
}
