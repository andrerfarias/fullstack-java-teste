package com.fullstack.dao;

import com.fullstack.model.Pedidos;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Andre
 */
public class PedidoDAO extends Connector implements DAO{
    @Override
    public Object getById(Long id) {
         try{
            return getEm().createQuery("SELECT p FROM Pedidos p LEFT JOIN FETCH p.pedidoProdutosCollection produtos LEFT JOIN FETCH produtos.produtoId WHERE p.id = :id ", Pedidos.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    @Override
    public Pedidos incluir(Object entity) {
        return (Pedidos) include(entity);
    }

    @Override
    public Pedidos atualizar(Object entity) {
        return (Pedidos) update(entity);
    }
    /**
     * Busca todos os pedidos no banco de dados
     * @author André Farias
     * @since 09/10/2016
     * @return uma lista de pedidos representados por com.fullstack.model.Pedidos
     */
    public List<Pedidos> getAll(){
        return getEm().createQuery("SELECT p FROM Pedidos p WHERE p.status.nome = 'ativo' ", Pedidos.class).getResultList();
    }
 
}
