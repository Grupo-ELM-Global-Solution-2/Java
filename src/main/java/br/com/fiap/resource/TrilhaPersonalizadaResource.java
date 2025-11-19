package br.com.fiap.resource;

import br.com.fiap.bo.TrilhaPersonalizadaBO;
import br.com.fiap.to.TrilhaPersonalizadaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * <p>Classe Resource que expõe os endpoints REST para
 * operações relacionadas a trilhas personalizadas.</p>
 *
 * <p>Utiliza a {@link TrilhaPersonalizadaBO} para manipulação de dados.</p>
 *
 * Endpoints disponíveis:
 * <ul>
 * <li>GET /trilhapersonalizada - Retorna todas as trilhas personalizadas</li>
 * <li>GET /trilhapersonalizada/{id_trilha_pers} - Retorna trilha pelo ID</li>
 * <li>GET /trilhapersonalizada/usuario/{id_user} - Retorna todas as trilhas de um usuário</li>
 * <li>POST /trilhapersonalizada - Cadastra uma nova trilha</li>
 * <li>PUT /trilhapersonalizada/{id_trilha_pers} - Atualiza trilha existente</li>
 * <li>DELETE /trilhapersonalizada/{id_trilha_pers} - Remove trilha pelo ID</li>
 * </ul>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
@Path("/trilhapersonalizada")
public class TrilhaPersonalizadaResource {
    private TrilhaPersonalizadaBO trilhaPersonalizadaBO = new TrilhaPersonalizadaBO();

    /**
     * Retorna todas as trilhas personalizadas cadastradas no sistema.
     *
     * @return Response com status 200 (OK) e a lista de {@link TrilhaPersonalizadaTO},
     * ou 404 (Not Found) se nenhuma trilha for encontrada.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<TrilhaPersonalizadaTO> resultado = trilhaPersonalizadaBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca uma trilha personalizada específica pelo seu ID.
     *
     * @param codigo O ID (id_trilha_pers) da trilha personalizada a ser buscada.
     * @return Response com status 200 (OK) e o {@link TrilhaPersonalizadaTO} encontrado,
     * ou 404 (Not Found) se o ID não existir.
     */
    @GET
    @Path("/{id_trilha_pers}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_trilha_pers") Long codigo) {
        TrilhaPersonalizadaTO resultado = trilhaPersonalizadaBO.findByCodigo(codigo);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca todas as trilhas personalizadas criadas por um usuário específico.
     *
     * @param idUser O ID (id_user) do usuário.
     * @return Response com status 200 (OK) e a lista de {@link TrilhaPersonalizadaTO} do usuário,
     * ou 404 (Not Found) se o usuário não tiver trilhas ou não existir.
     */
    @GET
    @Path("/usuario/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByUserId(@PathParam("id_user") Long idUser) {
        ArrayList<TrilhaPersonalizadaTO> resultado = trilhaPersonalizadaBO.findByUserId(idUser);
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }


    /**
     * Cadastra uma nova trilha personalizada no sistema.
     *
     * @param trilhaP O objeto {@link TrilhaPersonalizadaTO} contendo os dados da nova trilha.
     * @return Response com status 201 (Created) e o objeto {@link TrilhaPersonalizadaTO} salvo,
     * ou 400 (Bad Request) se houver erro na validação ou gravação.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid TrilhaPersonalizadaTO trilhaP) {
        TrilhaPersonalizadaTO resultado = trilhaPersonalizadaBO.save(trilhaP);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Atualiza uma trilha personalizada existente.
     *
     * @param trilhaP      O objeto {@link TrilhaPersonalizadaTO} com os dados atualizados.
     * @param idTrilhaPers O ID (id_trilha_pers) da trilha a ser atualizada, vindo da URL.
     * @return Response com status 201 (Created) e o objeto {@link TrilhaPersonalizadaTO} atualizado,
     * ou 400 (Bad Request) se houver erro na atualização.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_trilha_pers}")
    public Response update(@Valid TrilhaPersonalizadaTO trilhaP, @PathParam("id_trilha_pers") Long idTrilhaPers) {
        trilhaP.setIdTrilhaPers(idTrilhaPers);
        TrilhaPersonalizadaTO resultado = trilhaPersonalizadaBO.update(trilhaP);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Remove uma trilha personalizada do sistema pelo seu ID.
     *
     * @param codigo O ID (id_trilha_pers) da trilha a ser removida.
     * @return Response com status 204 (No Content) se a remoção for bem-sucedida,
     * ou 404 (Not Found) se a trilha não for encontrada.
     */
    @DELETE
    @Path("/{id_trilha_pers}")
    public Response delete(@PathParam("id_trilha_pers") Long codigo) {
        Response.ResponseBuilder response = (trilhaPersonalizadaBO.delete(codigo)) ? Response.status(204) : Response.status(404);
        return response.build();
    }
}