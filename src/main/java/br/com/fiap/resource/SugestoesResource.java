package br.com.fiap.resource;

import br.com.fiap.bo.SugestoesBO;
import br.com.fiap.to.SugestoesTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>Classe Resource que expõe os endpoints REST para operações relacionadas a sugestões.</p>
 * <p>Utiliza a {@link SugestoesBO} para manipulação de dados.</p>
 *
 * Endpoints disponíveis:
 * <ul>
 * <li>GET /sugestoes - Retorna todas as sugestões</li>
 * <li>GET /sugestoes/{id_sugestao} - Retorna sugestão pelo ID</li>
 * <li>POST /sugestoes - Cadastra uma nova sugestão</li>
 * <li>PUT /sugestoes/{id_sugestao} - Atualiza sugestão existente</li>
 * <li>DELETE /sugestoes/{id_sugestao} - Remove sugestão pelo ID</li>
 * </ul>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
@Path("/sugestoes")
public class SugestoesResource {
    private SugestoesBO sugestoesBO = new SugestoesBO();

    /**
     * Retorna todas as sugestões.
     *
     * @return Response com status 200 (OK) e lista de {@link SugestoesTO}, ou 404 se não houver dados.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<SugestoesTO> resultado = sugestoesBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca sugestão pelo ID.
     *
     * @param codigo ID da sugestão.
     * @return Response com status 200 (OK) e {@link SugestoesTO}, ou 404 se não encontrada.
     */
    @GET
    @Path("/{id_sugestao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_sugestao") Long codigo) throws SQLException {
        SugestoesTO resultado = sugestoesBO.findByCodigo(codigo);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Cadastra uma nova sugestão.
     *
     * @param sugestao {@link SugestoesTO} com os dados da sugestão.
     * @return Response com status 201 (CREATED) e {@link SugestoesTO}, ou 400 se falhar.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid SugestoesTO sugestao) {
        SugestoesTO resultado = sugestoesBO.save(sugestao);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Atualiza uma sugestão existente.
     *
     * @param sugestao {@link SugestoesTO} com dados atualizados.
     * @param idSugestao ID da sugestão a ser atualizada.
     * @return Response com status 201 (CREATED) e {@link SugestoesTO}, ou 400 se falhar.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_sugestao}")
    public Response update(@Valid SugestoesTO sugestao, @PathParam("id_sugestao") Long idSugestao) {
        sugestao.setIdSugestoes(idSugestao);
        SugestoesTO resultado = sugestoesBO.update(sugestao);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Remove sugestão pelo ID.
     *
     * @param codigo ID da sugestão a ser removida.
     * @return Response com status 204 (NO CONTENT) se removida, ou 404 se não encontrada.
     */
    @DELETE
    @Path("/{id_sugestao}")
    public Response delete(@PathParam("id_sugestao") Long codigo) {
        Response.ResponseBuilder response = (sugestoesBO.delete(codigo)) ? Response.status(204) : Response.status(404);
        return response.build();
    }
}