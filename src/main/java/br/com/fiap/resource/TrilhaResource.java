package br.com.fiap.resource;

import br.com.fiap.bo.TrilhaBO;
import br.com.fiap.to.TrilhaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * <p>Classe Resource que expõe os endpoints REST para operações relacionadas a trilhas.</p>
 * <p>Utiliza a {@link TrilhaBO} para manipulação de dados.</p>
 *
 * Endpoints disponíveis:
 * <ul>
 * <li>GET /trilha - Retorna todas as trilhas</li>
 * <li>GET /trilha/{id_trilha} - Retorna trilha pelo ID</li>
 * <li>POST /trilha - Cadastra uma nova trilha</li>
 * <li>PUT /trilha/{id_trilha} - Atualiza trilha existente</li>
 * <li>DELETE /trilha/{id_trilha} - Remove trilha pelo ID</li>
 * </ul>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
@Path("/trilha")
public class TrilhaResource {
    private TrilhaBO trilhaBO = new TrilhaBO();

    /**
     * Retorna todas as trilhas.
     *
     * @return Response com status 200 (OK) e lista de {@link TrilhaTO}, ou 404 se não houver dados.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<TrilhaTO> resultado = trilhaBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca trilha pelo ID.
     *
     * @param codigo ID da trilha.
     * @return Response com status 200 (OK) e {@link TrilhaTO}, ou 404 se não encontrada.
     */
    @GET
    @Path("/{id_trilha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_trilha") Long codigo) {
        TrilhaTO resultado = trilhaBO.findByCodigo(codigo);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Cadastra uma nova trilha.
     *
     * @param trilha {@link TrilhaTO} com os dados da trilha.
     * @return Response com status 201 (CREATED) e {@link TrilhaTO}, ou 400 se falhar.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid TrilhaTO trilha) {
        TrilhaTO resultado = trilhaBO.save(trilha);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * ATUALIZADO: Método PUT (update) adicionado para consistência.
     * Atualiza uma trilha existente.
     *
     * @param trilha {@link TrilhaTO} com dados atualizados.
     * @param idTrilha ID da trilha a ser atualizada.
     * @return Response com status 201 (CREATED) e {@link TrilhaTO}, ou 400 se falhar.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_trilha}")
    public Response update(@Valid TrilhaTO trilha, @PathParam("id_trilha") Long idTrilha) {
        trilha.setIdTrilha(idTrilha);
        TrilhaTO resultado = trilhaBO.update(trilha);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }


    /**
     * Remove trilha pelo ID.
     *
     * @param codigo ID da trilha a ser removida.
     * @return Response com status 204 (NO CONTENT) se removida, ou 404 se não encontrada.
     */
    @DELETE
    @Path("/{id_trilha}")
    public Response delete(@PathParam("id_trilha") Long codigo) {
        Response.ResponseBuilder response = (trilhaBO.delete(codigo)) ? Response.status(204) : Response.status(404);
        return response.build();
    }
}