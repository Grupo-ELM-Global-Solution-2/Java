package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>Classe de negócios (Business Object) que gerencia operações relacionadas a usuários.</p>
 * <p>Utiliza a {@link br.com.fiap.dao.UsuarioDAO} para acessar o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    /**
     * Retorna todos os usuários cadastrados no banco de dados.
     *
     * @return ArrayList de {@link UsuarioTO} contendo todos os usuários.
     */
    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAll();
    }

    /**
     * Busca um usuário pelo seu código (ID).
     *
     * @param codigo Código (ID) do usuário.
     * @return {@link UsuarioTO} correspondente ao código informado ou null se não encontrado.
     */
    public UsuarioTO findByCodigo(Long codigo) throws SQLException {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findByCodigo(codigo);
    }

    /**
     * Busca um usuário pelo email.
     *
     * @param email Email do usuário.
     * @return {@link UsuarioTO} correspondente ao email ou null.
     */
    public UsuarioTO findByEmail(String email) throws SQLException {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findByEmail(email);
    }

    /**
     * Salva um novo usuário no banco de dados.
     *
     * @param usuario Objeto {@link UsuarioTO} contendo os dados do usuário a ser cadastrado.
     * @return {@link UsuarioTO} salvo, ou null se não foi possível salvar.
     */
    public UsuarioTO save(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.save(usuario);
    }

    /**
     * Exclui um usuário do banco de dados pelo seu código (ID).
     *
     * @param codigo Código (ID) do usuário a ser excluído.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    public boolean delete(Long codigo) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.delete(codigo);
    }

    /**
     * Atualiza os dados de um usuário existente no banco de dados.
     *
     * @param usuario Objeto {@link UsuarioTO} contendo os dados atualizados do usuário.
     * @return {@link UsuarioTO} atualizado, ou null se não foi possível atualizar.
     */
    public UsuarioTO update(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.update(usuario);
    }
}
