package br.com.fiap.resource;

import br.com.fiap.bo.ProgressoBO;
import br.com.fiap.to.ProgressoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * <p>Classe Resource que expõe os endpoints REST para operações relacionadas a progressos.</p>
 * <p>Utiliza a {@link ProgressoBO} para manipulação de dados.</p>
 *
 * Endpoints disponíveis:
 * <ul>
 *     <li>GET /progresso - Retorna todos os progressos</li>
 *     <li>GET /progresso/{id_progresso} - Retorna progresso pelo ID</li>
 *     <li>POST /progresso - Cadastra uma nova progresso</li>
 *     <li>PUT /progresso/{id_progresso} - Atualiza progresso existente</li>
 *     <li>DELETE /progresso/{id_progresso} - Remove progresso pelo ID</li>
 * </ul>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
@Path("/progresso")
public class ProgressoResource {
    private ProgressoBO progressoBO = new ProgressoBO();

    /**
     * Retorna todos os progressos.
     *
     * @return Response com status 200 (OK) e lista de {@link ProgressoTO}, ou 404 se não houver dados.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ProgressoTO> resultado = progressoBO.findAll();
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca progresso pelo ID.
     *
     * @param codigo ID do progresso.
     * @return Response com status 200 (OK) e {@link ProgressoTO}, ou 404 se não encontrado.
     */
    @GET
    @Path("/{id_progresso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_progresso") Long codigo) {
        ProgressoTO resultado = progressoBO.findByCodigo(codigo);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Cadastra uma nova progresso.
     *
     * @param progresso {@link ProgressoTO} com os dados do progresso.
     * @return Response com status 201 (CREATED) e {@link ProgressoTO}, ou 400 se falhar.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ProgressoTO progresso) {
        ProgressoTO resultado = progressoBO.save(progresso);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Atualiza uma progresso existente.
     *
     * @param progresso {@link ProgressoTO} com dados atualizados.
     * @param idProgresso ID do progresso a ser atualizado.
     * @return Response com status 201 (CREATED) e {@link ProgressoTO}, ou 400 se falhar.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_progresso}")
    public Response update(@Valid ProgressoTO progresso, @PathParam("id_progresso") Long idProgresso) {
        progresso.setIdProgresso(idProgresso);
        ProgressoTO resultado = progressoBO.update(progresso);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Remove progresso pelo ID.
     *
     * @param codigo ID do progresso a ser removido.
     * @return Response com status 204 (NO CONTENT) se removido, ou 404 se não encontrado.
     */
    @DELETE
    @Path("/{id_progresso}")
    public Response delete(@PathParam("id_progresso") Long codigo) {
        Response.ResponseBuilder response = (progressoBO.delete(codigo)) ? Response.status(204) : Response.status(404);
        return response.build();
    }
}
