package br.com.fiap.resource;

import br.com.fiap.bo.ModuloBO;
import br.com.fiap.to.ModuloTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>Classe Resource que expõe os endpoints REST para operações relacionadas a modulos.</p>
 * <p>Utiliza a {@link ModuloBO} para manipulação de dados.</p>
 *
 * Endpoints disponíveis:
 * <ul>
 *     <li>GET /modulo - Retorna todos os modulos</li>
 *     <li>GET /modulo/{id_modulo} - Retorna modulo pelo ID</li>
 *     <li>POST /modulo - Cadastra uma nova modulo</li>
 *     <li>PUT /modulo/{id_modulo} - Atualiza modulo existente</li>
 *     <li>DELETE /modulo/{id_modulo} - Remove modulo pelo ID</li>
 * </ul>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
@Path("/modulo")
public class ModuloResource {
    private ModuloBO moduloBO = new ModuloBO();

    /**
     * Retorna todos os modulos.
     *
     * @return Response com status 200 (OK) e lista de {@link ModuloTO}, ou 404 se não houver dados.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ModuloTO> resultado = moduloBO.findAll();
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca modulo pelo ID.
     *
     * @param codigo ID do modulo.
     * @return Response com status 200 (OK) e {@link ModuloTO}, ou 404 se não encontrado.
     */
    @GET
    @Path("/{id_modulo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_modulo") Long codigo) throws SQLException {
        ModuloTO resultado = moduloBO.findByCodigo(codigo);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Cadastra uma nova modulo.
     *
     * @param modulo {@link ModuloTO} com os dados do modulo.
     * @return Response com status 201 (CREATED) e {@link ModuloTO}, ou 400 se falhar.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ModuloTO modulo) {
        ModuloTO resultado = moduloBO.save(modulo);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Atualiza uma modulo existente.
     *
     * @param modulo {@link ModuloTO} com dados atualizados.
     * @param idModulo ID do modulo a ser atualizado.
     * @return Response com status 201 (CREATED) e {@link ModuloTO}, ou 400 se falhar.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_modulo}")
    public Response update(@Valid ModuloTO modulo, @PathParam("id_modulo") Long idModulo) {
        modulo.setIdModulo(idModulo);
        ModuloTO resultado = moduloBO.update(modulo);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Remove modulo pelo ID.
     *
     * @param codigo ID do modulo a ser removido.
     * @return Response com status 204 (NO CONTENT) se removido, ou 404 se não encontrado.
     */
    @DELETE
    @Path("/{id_modulo}")
    public Response delete(@PathParam("id_modulo") Long codigo) {
        Response.ResponseBuilder response = (moduloBO.delete(codigo)) ? Response.status(204) : Response.status(404);
        return response.build();
    }
}
