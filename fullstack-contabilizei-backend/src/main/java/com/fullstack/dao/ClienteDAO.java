package com.fullstack.dao;

import com.fullstack.model.Clientes;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
/**
 *
 * @author Andre
 */
public class ClienteDAO extends Connector implements DAO {
   
    public ClienteDAO() {  }
    /**
     * Recuperar um único cliente conforme o ID informado
     * @param id
     * @return um único Cliente representado pela entidade com.fullstack.model.Clientes
     */
    @Override
    public Clientes getById(Long id) {
        try{
            return getEm().createQuery("SELECT cliente FROM Clientes cliente WHERE cliente.id = :id ", Clientes.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public Clientes incluir(Object entity) {
        return (Clientes) include(entity);
    }

    @Override
    public Clientes atualizar(Object entity) {
        return (Clientes) update(entity);
    }

     /**
     * Busca os clientes de acordo com os parâmetros de busca informados
     * @author André Farias
     * @param documento
     * @param email
     * @return uma lista de clientes representados pela entidade com.fullstack.model.Clientes
     */
    public List<Clientes> getClientes(Long documento, String email){
        String sql = "";
        //FILTRA A QUERY DE ACORDO COM OS PARAMS
        sql += ((documento != null) ? ((sql.equals("")) ? " WHERE " : " AND ")+" clientes.documento = :documento" :  "");
        sql += ((email != null) ? ((sql.equals("")) ? " WHERE " : " AND ")+" clientes.email LIKE :email " :  "");
        //REALIZA A QUERY NO BANCO DE DADOS
        Query query = getEm().createQuery("SELECT clientes FROM Clientes clientes "+sql);
        if(documento != null){ query.setParameter("documento", documento); }
        if(email != null){ query.setParameter("email", "%"+email+"%"); }
        //RETORNA O RESULTADO COMO UMA LISTA
        return query.getResultList();
    }
}
