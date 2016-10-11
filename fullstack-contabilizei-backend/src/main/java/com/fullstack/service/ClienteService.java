package com.fullstack.service;
import com.fullstack.job.ClienteJob;
import com.fullstack.model.Clientes;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author Andre Farias
 * @since 08/10/2016
 */
@Path("/cliente")
public class ClienteService {
    private final Response NOT_FOUND = Response.status(Response.Status.NOT_FOUND).entity("").build();

    public ClienteService() {}
    
    /**
     * Recupera e retorna um JSON representando os clientes encontrados de acordo com os parâmetros informados
     * caso o cliente não exista retorna 404 NOT FOUND
     * @param documento
     * @param email
     * @since  08/10/2016
     * @return JSON representando uma lista de objetos com.fullstack.model.Clientes
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientes(@QueryParam("documento") Long documento, @QueryParam("email") String email){
        ClienteJob clienteJob = new ClienteJob();
        List<Clientes> clientes = clienteJob.getClientes(documento, email);
        if(clientes == null || clientes.isEmpty()){ return NOT_FOUND; }
        return Response.ok(clientes).build();
    }
    
    /**
     * Recupera e retorna um JSON representando o objeto cliente de acordo com o ID informado, 
     * caso o cliente não exista retorna 404 NOT FOUND
     * @param id
     * @since  08/10/2016
     * @return JSON representando o objeto com.fullstack.model.Clientes
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliente(@PathParam("id") Long id){
        ClienteJob clienteJob = new ClienteJob();
        Clientes cliente = clienteJob.getById(id);
        if(cliente == null){ return NOT_FOUND; }
        return Response.ok(cliente).build();
    }
}
