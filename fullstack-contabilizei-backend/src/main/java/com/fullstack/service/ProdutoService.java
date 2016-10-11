package com.fullstack.service;
import com.fullstack.job.ProdutoJob;
import com.fullstack.model.Produtos;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Andre
 */
@Path("/produto")
public class ProdutoService {
    @Context
    private UriInfo context;
    //CONSTANTES DE RETORNO
    private final Response NOT_FOUND = Response.status(Response.Status.NOT_FOUND).entity("").build();
     //CONSTRUTOR
    public ProdutoService() {}
    /**
     * Busca todos os produtos criados
     * @author André Farias
     * @since 08/10/2016
     * @return uma lista de produtos representados por com.fullstack.model.Produtos
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutos(){
        ProdutoJob produtoJob = new ProdutoJob();
        List<Produtos> produtos = produtoJob.getProdutos();
        return ((produtos != null && !produtos.isEmpty()) ? Response.ok(produtos).build() : NOT_FOUND );
    }
}
