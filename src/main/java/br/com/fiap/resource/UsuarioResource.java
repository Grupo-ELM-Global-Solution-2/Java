package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * <p>Classe Resource que expõe os endpoints REST para operações relacionadas a usuários.</p>
 * <p>Utiliza a {@link br.com.fiap.bo.UsuarioBO} para manipulação de dados.</p>
 *
 * Endpoints disponíveis:
 * <ul>
 *     <li>GET /usuario - Retorna todos os usuários</li>
 *     <li>GET /usuario/{id_user} - Retorna usuário pelo ID</li>
 *     <li>POST /usuario - Cadastra um novo usuário</li>
 *     <li>PUT /usuario/{id_user} - Atualiza usuário existente</li>
 *     <li>DELETE /usuario/{id_user} - Remove usuário pelo ID</li>
 * </ul>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
@Path("/usuario")
public class UsuarioResource {
    private UsuarioBO usuarioBO = new UsuarioBO();

    /**
     * Retorna todos os usuários.
     *
     * @return Response com status 200 (OK) e lista de {@link UsuarioTO}, ou 404 se não houver dados.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<UsuarioTO> resultado = usuarioBO.findAll();
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca usuário pelo ID.
     *
     * @param codigo ID do usuário.
     * @return Response com status 200 (OK) e {@link UsuarioTO}, ou 404 se não encontrado.
     */
    @GET
    @Path("/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_user") Long codigo) {
        UsuarioTO resultado = usuarioBO.findByCodigo(codigo);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Endpoint para buscar usuário pelo email.
     *
     * @param email Email do usuário, vindo da URL.
     * @return Response com status 200 (OK) e o {@link UsuarioTO} completo,
     * ou 404 (Not Found) se o email não estiver cadastrado.
     */
    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmail(@PathParam("email") String email) {
        if (email == null || email.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("O email é obrigatório.").build();
        }
        UsuarioTO resultado = usuarioBO.findByEmail(email);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok(resultado) : Response.status(Response.Status.NOT_FOUND);
        return response.build();
    }

    /**
     * Cadastra um novo usuário.
     *
     * @param usuario {@link UsuarioTO} com os dados do usuário.
     * @return Response com status 201 (CREATED) e {@link UsuarioTO}, ou 400 se falhar.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid UsuarioTO usuario) {
        UsuarioTO resultado = usuarioBO.save(usuario);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Atualiza um usuário existente.
     *
     * @param usuario {@link UsuarioTO} com dados atualizados.
     * @param idUser ID do usuário a ser atualizado.
     * @return Response com status 201 (CREATED) e {@link UsuarioTO}, ou 400 se falhar.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_user}")
    public Response update(@Valid UsuarioTO usuario, @PathParam("id_user") Long idUser) {
        usuario.setIdUser(idUser);
        UsuarioTO resultado = usuarioBO.update(usuario);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Remove usuário pelo ID.
     *
     * @param codigo ID do usuário a ser removido.
     * @return Response com status 204 (NO CONTENT) se removido, ou 404 se não encontrado.
     */
    @DELETE
    @Path("/{id_user}")
    public Response delete(@PathParam("id_user") Long codigo) {
        Response.ResponseBuilder response = (usuarioBO.delete(codigo)) ? Response.status(204) : Response.status(404);
        return response.build();
    }
}
